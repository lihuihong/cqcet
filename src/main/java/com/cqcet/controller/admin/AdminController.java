package com.cqcet.controller.admin;

import com.cqcet.entity.Article;
import com.cqcet.entity.College;
import com.cqcet.entity.Result;
import com.cqcet.services.*;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 那个谁 on 2018/9/13.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private CollegeService collegeService;

    @Autowired
    private UserService userService;


    /**
     * 查询所有帖子
     * @param map
     * @param typeId
     * @param startDate
     * @param endDate
     * @param keyWord
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/list.action")
    public String list(ModelMap map,
                       @RequestParam(required = false, value = "typeId") String typeId,
                       @RequestParam(required = false, value = "startDate") String startDate,
                       @RequestParam(required = false, value = "endDate") String endDate,
                       @RequestParam(required = false, value = "keyWord") String keyWord,
                       @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                       @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("typeId", typeId);
        param.put("startDate", startDate);
        param.put("endDate", endDate);
        if (!StringUtils.isEmpty(keyWord)) {
            param.put("keyWord", "%" + keyWord.trim() + "%");
        }

        param.put("status", 0);

        // pageHelper分页插件
        // 只需要在查询之前调用，传入当前页码，以及每一页显示多少条
        PageMethod.startPage(pageNum, pageSize);
        List<Article> list = articleService.list(param);
        PageInfo<Article> pageInfo = new PageInfo<Article>(list);
        map.put("pageInfo", pageInfo);

        // 查询所有文章分类
        map.put("typeList", typeService.list());

        map.put("typeId", typeId);
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("keyWord", keyWord);

        return "admin/list_normal";
    }

    /**
     * 个人帖子列表
     * @param map
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/user_article.action")
    public String userArticle(ModelMap map,@RequestParam(required = false,value = "userId") String userId,
                              @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                              @RequestParam(value = "pageSize", defaultValue = "5") int pageSize){
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId",userId);
        param.put("status", 0);

        // pageHelper分页插件
        // 只需要在查询之前调用，传入当前页码，以及每一页显示多少条
        PageMethod.startPage(pageNum, pageSize);
        List<Article> list = articleService.list(param);
        PageInfo<Article> pageInfo = new PageInfo<Article>(list);
        map.put("pageInfo", pageInfo);
        map.put("username",list.get(0).getUsername());
        // 查询所有文章分类
        map.put("typeList", typeService.list());

        return "admin/user_article";
    }

    /**
     * 回收站
     * @param map
     * @param typeId
     * @param startDate
     * @param endDate
     * @param keyWord
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/recycle.action")
    public String listRecycle(ModelMap map,
                              @RequestParam(required = false, value = "typeId") String typeId,
                              @RequestParam(required = false, value = "startDate") String startDate,
                              @RequestParam(required = false, value = "endDate") String endDate,
                              @RequestParam(required = false, value = "keyWord") String keyWord,
                              @RequestParam(value="pageNum", defaultValue="1") int pageNum,
                              @RequestParam(value="pageSize", defaultValue="5") int pageSize) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("typeId", typeId);
        param.put("startDate", startDate);
        param.put("endDate", endDate);
        if (!StringUtils.isEmpty(keyWord)) {
            param.put("keyWord", "%" + keyWord.trim() + "%");
        }

        param.put("status", 1);

        // pageHelper分页插件
        // 只需要在查询之前调用，传入当前页码，以及每一页显示多少条
        PageMethod.startPage(pageNum, pageSize);
        List<Article> list = articleService.list(param);
        PageInfo<Article> pageInfo = new PageInfo<Article>(list);
        map.put("pageInfo", pageInfo);

        // 查询所有文章分类
        map.put("typeList", typeService.list());

        map.put("typeId", typeId);
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("keyWord", keyWord);

        return "admin/recycle";
    }

    /**
     * 帖子编辑
     * @param map
     * @param id
     * @return
     */
    @RequestMapping("/edit.action")
    public String edit(ModelMap map, @RequestParam(required = false, value = "id") String id) {

        // 单个文章的信息
        if (!StringUtils.isEmpty(id)) {
            Article article = articleService.selectById(id);
            map.put("article", article);
        }
        List<College> collegeList = collegeService.list();
        // 查询所有文章分类
        map.put("typeList", typeService.list());
        map.put("collegeList", collegeList);
        map.put("user",userService.list_article());
        return "admin/edit";
    }

    /**
     * 批量移动帖子到分类
     * @param idArr
     * @param typeId
     * @return
     */
    @RequestMapping(value = "/move.json ", produces="application/json;charset=UTF-8;")
    @ResponseBody
    public Result move(
            @RequestParam(value = "idArr") String[] idArr,
            @RequestParam(value = "typeId") String typeId) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("idArr", idArr);
        param.put("typeId", typeId);

        articleService.bacthUpdate(param);

        return Result.success();
    }

    /**
     * 批量删除文章
     * @return
     */
    @RequestMapping("/delete.json")
    @ResponseBody
    public Result delete(
            @RequestParam(value = "idArr") String[] idArr) {

        articleService.batchDelete(idArr);

        return Result.success();
    }

    /**
     * 帖子编辑保存
     * @param article
     * @return
     */
    @RequestMapping("/save.json")
    @ResponseBody
    public Result save(Article article){

        articleService.save(article);

        return Result.success();
    }

    /**
     * 上传文件到七牛云
     *
     * @throws IOException
     */
    @RequestMapping("/upload.json")
    @ResponseBody
    public Result upload(MultipartFile file) throws IOException {

        /**
         * 构造一个带指定Zone对象的配置类 华东 : Zone.zone0() 华北 : Zone.zone1() 华南 : Zone.zone2() 北美 :
         * Zone.zoneNa0()
         */
        Configuration cfg = new Configuration(Zone.zone0());
        // ...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        // ...生成上传凭证，然后准备上传
        String accessKey = "usvmdc4mAZAL3bhwLkm5iMk50aq-31Dl_U8b_P5j";
        String secretKey = "VI2N4hgyhNO7QrJcGFARIL70A6S6rNaoWD6m-lM6";
        String bucket = "heylhh";
        // 默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;

        String imgUrl = "";
        try {
            // 数据流上传
            InputStream byteInputStream = file.getInputStream();
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(byteInputStream, key, upToken, null, null);
                // 解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
                imgUrl = putRet.hash;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    // ignore
                }
            }
        } catch (UnsupportedEncodingException ex) {
            // ignore
        }

        return Result.success().add("imgUrl", "http://heylhh.com/" + imgUrl);
    }


}
