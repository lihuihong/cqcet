package com.cqcet.entity;

import lombok.Data;

/**
 * Created by  那个谁 on 2018/10/14.
 */
@Data
public class UploadInfo {
    private String id;
    private String type;
    private String domain;
    private String ak;
    private String sk;
    private String bucket;
    private String compress;
}
