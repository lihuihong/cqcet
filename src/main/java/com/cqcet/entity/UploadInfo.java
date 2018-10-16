package com.cqcet.entity;

import lombok.Data;

/**
 * Created by 那个谁 on 2018/10/14.
 */
@Data
public class UploadInfo {
    private String id;			// 主键
    private String type;		// 类型（七牛云）
    private String domain;		// 域名
    private String ak;			// ak
    private String sk;			// sk
    private String bucket;		// 空间名称
    private String compress;	// 压缩率
}
