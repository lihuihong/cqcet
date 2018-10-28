package com.cqcet.dao;

import com.cqcet.entity.UploadInfo;

/**
 * Created by 那个谁 on 2018/10/14.
 */
public interface UploadInfoMapper {

    UploadInfo selectByType(String type);


    int update(UploadInfo uploadInfo);
}
