package com.cqcet.dao;

import com.cqcet.entity.UploadInfo;

/**
 * ��ţ��
 * Created by �Ǹ�˭ on 2018/10/14.
 */
public interface UploadInfoMapper {
    /**
     * �������ͣ���ѯ�ϴ�������Ϣ
     * @param type ����
     * @return
     */
    UploadInfo selectByType(String type);

    /**
     * �����ϴ�����
     * @param uploadInfo
     * @return
     */
    int update(UploadInfo uploadInfo);
}
