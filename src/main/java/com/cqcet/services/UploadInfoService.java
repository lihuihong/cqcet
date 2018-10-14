package com.cqcet.services;

import com.cqcet.entity.UploadInfo;
import com.cqcet.dao.UploadInfoMapper;
import com.cqcet.exception.LException;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * Created by �Ǹ�˭ on 2018/10/14.
 */
@Service("UploadInfoService")
public class UploadInfoService {
    @Autowired
    private UploadInfoMapper uploadInfoMapper;

    /**
     * �������ͣ���ѯ�ϴ�������Ϣ
     * @param type ����
     * @return
     */
    public UploadInfo selectByType(String type) {
        return uploadInfoMapper.selectByType(type);
    }

    /**
     * �����ϴ�����
     * @param boardInfo
     */
    public void save(UploadInfo boardInfo) {
        uploadInfoMapper.update(boardInfo);
    }

    /**
     * �ϴ�����ͼƬ����ţ��
     * @param file
     * @param uploadInfo
     * @return
     * @throws LException
     * @throws IOException
     */
    public String uploadImage(MultipartFile file, UploadInfo uploadInfo) throws LException, IOException {
        /**
         * ����һ����ָ��Zone�����������
         * ���� : Zone.zone0()
         * ���� : Zone.zone1()
         * ���� : Zone.zone2()
         * ���� : Zone.zoneNa0()
         */
        Configuration cfg = new Configuration(Zone.zone0());
        // ...���������ο���ע��
        UploadManager uploadManager = new UploadManager(cfg);
        // ...�����ϴ�ƾ֤��Ȼ��׼���ϴ�
        String accessKey = uploadInfo.getAk();
        String secretKey = uploadInfo.getSk();
        String bucket = uploadInfo.getBucket();
        String compress = uploadInfo.getCompress();

        // Ĭ�ϲ�ָ��key������£����ļ����ݵ�hashֵ��Ϊ�ļ���
        String key = null;
        String imgUrl = "";
        try {
            // �������ϴ�
            InputStream byteInputStream = file.getInputStream();
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(byteInputStream, key, upToken, null, null);
                // �����ϴ��ɹ��Ľ��
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
//				System.out.println(putRet.key);
//				System.out.println(putRet.hash);
                imgUrl = uploadInfo.getDomain() + putRet.hash;

                // ͼƬѹ�����ٴ��ϴ�
                if ("0".equals(compress) || "100".equals(compress)) {
                    // ��ѹ��
                } else {
                    imgUrl = uploadCompressImage(uploadInfo, auth, cfg, bucket, imgUrl);
                    // ɾ��ԭͼ
                    String deleteKey = putRet.hash;
                    deleteFile(auth, cfg, bucket, deleteKey);
                }
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    // ignore
                }
                throw new LException("�ϴ�ʧ�ܣ����������粻�ȶ�");
            }
        } catch (UnsupportedEncodingException ex) {
            // ignore
            throw new LException("�ϴ�ʧ�ܣ����������粻�ȶ�");
        }

        return imgUrl;
    }

    /**
     * ͼƬѹ�����ٴ��ϴ�
     * @param uploadInfo
     * @param auth
     * @param cfg
     * @param bucket
     * @param imgUrl
     * @return
     * @throws LException
     */
    private String uploadCompressImage(UploadInfo uploadInfo, Auth auth, Configuration cfg, String bucket, String imgUrl) throws LException {
        String compress = uploadInfo.getCompress();
        String apiCut = "imageView2/0/q/"+compress+"|imageslim";

        // ʵ����һ��BucketManager����
        BucketManager bucketManager = new BucketManager(auth, cfg);
        // Ҫfetch��url
        String url = imgUrl + apiCut;
//		System.out.println(url);

        try {
            // ����fetch����ץȡ�ļ�
            String hash = bucketManager.fetch(url, bucket, null).hash;
//			System.out.println(hash);

            return uploadInfo.getDomain() + hash;
        } catch (QiniuException e) {
            e.printStackTrace();
            throw new LException("�ϴ�ʧ�ܣ�ͼƬ�ü�ʱ�����쳣");
        }
    }

    /**
     * �ϴ�ͷ�񣨱����ϴ���
     * @param file
     * @param uploadInfo
     * @return
     * @throws LException
     * @throws IOException
     */
    public String uploadAvatar(MultipartFile file, UploadInfo uploadInfo) throws LException, IOException {
        /**
         * ����һ����ָ��Zone�����������
         * ���� : Zone.zone0()
         * ���� : Zone.zone1()
         * ���� : Zone.zone2()
         * ���� : Zone.zoneNa0()
         */
        Configuration cfg = new Configuration(Zone.zone0());
        // ...���������ο���ע��
        UploadManager uploadManager = new UploadManager(cfg);
        // ...�����ϴ�ƾ֤��Ȼ��׼���ϴ�
        String accessKey = uploadInfo.getAk();
        String secretKey = uploadInfo.getSk();
        String bucket = uploadInfo.getBucket();
        // Ĭ�ϲ�ָ��key������£����ļ����ݵ�hashֵ��Ϊ�ļ���
        String key = null;

        String imgUrl = "";
        try {
            // �������ϴ�
            InputStream byteInputStream = file.getInputStream();
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(byteInputStream, key, upToken, null, null);
                // �����ϴ��ɹ��Ľ��
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
//				System.out.println(putRet.key);
//				System.out.println(putRet.hash);
                String deleteKey = putRet.hash;
                imgUrl = uploadInfo.getDomain() + putRet.hash;

                // ͼƬ�ü����ٴ��ϴ�
                imgUrl = uploadCutImage(uploadInfo, auth, cfg, bucket, imgUrl);
                // ɾ��ԭͼ
                deleteFile(auth, cfg, bucket, deleteKey);
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    // ignore
                }
                throw new LException("�ϴ�ʧ�ܣ����������粻�ȶ�");
            }
        } catch (UnsupportedEncodingException ex) {
            // ignore
            throw new LException("�ϴ�ʧ�ܣ����������粻�ȶ�");
        }

        return imgUrl;
    }

    /**
     * ͼƬ�ü����ٴ��ϴ�
     * @param uploadInfo
     * @param auth
     * @param cfg
     * @param bucket
     * @param imgUrl
     * @return
     * @throws LException
     */
    public String uploadCutImage(UploadInfo uploadInfo, Auth auth, Configuration cfg, String bucket, String imgUrl) throws LException {
        String width = "200";
        String height = "200";
        String apiCut = "?imageView2/1/w/"+width+"/h/"+height;

        // ʵ����һ��BucketManager����
        BucketManager bucketManager = new BucketManager(auth, cfg);
        // Ҫfetch��url
        String url = imgUrl + apiCut;
//		System.out.println(url);

        try {
            // ����fetch����ץȡ�ļ�
            String hash = bucketManager.fetch(url, bucket, null).hash;
//			System.out.println(hash);

            return uploadInfo.getDomain() + hash;
        } catch (QiniuException e) {
            e.printStackTrace();
            throw new LException("�ϴ�ʧ�ܣ�ͼƬ�ü�ʱ�����쳣");
        }
    }

    /**
     * ɾ��ԭͼ
     * @param auth
     * @param cfg
     * @param bucket
     * @param fileName
     */
    private void deleteFile(Auth auth, Configuration cfg, String bucket, String fileName) {
        //����һ����ָ��Zone�����������
//		Configuration cfg = new Configuration(Zone.zone0());
        //...���������ο���ע��
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, fileName);
        } catch (QiniuException ex) {
            // ��������쳣��˵��ɾ��ʧ��
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }

    /**
     * �ϴ�base64ͼƬ
     * @param file64
     * @param uploadInfo
     * @return
     * @throws IOException
     */
    public String uploadAvatarByBase64(String file64, UploadInfo uploadInfo) throws IOException {
        // ��Կ����
        String ak = uploadInfo.getAk();
        String sk = uploadInfo.getSk();
        Auth auth = Auth.create(ak, sk);

        // �ռ���
        String bucketname = uploadInfo.getBucket();
        // �ϴ���ͼƬ��
        String key = UUID.randomUUID().toString().replace("-", "");

        file64 = file64.substring(22);
//		System.out.println("file64:"+file64);
        String url = "http://upload.qiniu.com/putb64/" + -1 + "/key/" + UrlSafeBase64.encodeToString(key);
        // �ǻ����ռ���Ҫ����ע������ 1 �޸��ϴ�����
        RequestBody rb = RequestBody.create(null, file64);
        String upToken  = auth.uploadToken(bucketname, null, 3600, new StringMap().put("insertOnly", 1));
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/octet-stream")
                .addHeader("Authorization", "UpToken " + upToken)
                .post(rb).build();
//		System.out.println(request.headers());
        OkHttpClient client = new OkHttpClient();
        okhttp3.Response response = client.newCall(request).execute();
        System.out.println(response);

        String imgUrl = uploadInfo.getDomain() + key;

        return imgUrl;
    }

    /**
     * �ϴ��ļ�
     * @param file
     * @param uploadInfo
     * @return
     * @throws LException
     * @throws IOException
     */
    public String uploadFile(MultipartFile file, UploadInfo uploadInfo) throws LException, IOException {
        /**
         * ����һ����ָ��Zone�����������
         * ���� : Zone.zone0()
         * ���� : Zone.zone1()
         * ���� : Zone.zone2()
         * ���� : Zone.zoneNa0()
         */
        Configuration cfg = new Configuration(Zone.zone0());
        // ...���������ο���ע��
        UploadManager uploadManager = new UploadManager(cfg);
        // ...�����ϴ�ƾ֤��Ȼ��׼���ϴ�
        String accessKey = uploadInfo.getAk();
        String secretKey = uploadInfo.getSk();
        String bucket = uploadInfo.getBucket();

        // Ĭ�ϲ�ָ��key������£����ļ����ݵ�hashֵ��Ϊ�ļ���
        // �ļ�ԭ����
        String fileName = file.getOriginalFilename();
        String key = UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));
        String fileUrl = "";
        try {
            // �������ϴ�
            InputStream byteInputStream = file.getInputStream();
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(byteInputStream, key, upToken, null, null);
                // �����ϴ��ɹ��Ľ��
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
//				System.out.println(putRet.key);
//				System.out.println(putRet.hash);
                fileUrl = uploadInfo.getDomain() + putRet.key;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    // ignore
                }
                throw new LException("�ϴ�ʧ�ܣ����������粻�ȶ�");
            }
        } catch (UnsupportedEncodingException ex) {
            // ignore
            throw new LException("�ϴ�ʧ�ܣ����������粻�ȶ�");
        }

        return fileUrl;
    }
}

