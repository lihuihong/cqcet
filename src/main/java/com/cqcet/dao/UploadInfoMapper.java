package com.cqcet.dao;

import com.cqcet.entity.UploadInfo;

/**
 * 七牛云
 * Created by 那个谁 on 2018/10/14.
 */
public interface UploadInfoMapper {
    /**
     * 根据类型，查询上传设置信息
     * @param type 类型
     * @return
     */
    UploadInfo selectByType(String type);

    /**
     * 更新上传设置
     * @param uploadInfo
     * @return
     */
    int update(UploadInfo uploadInfo);
}
