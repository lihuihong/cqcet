package com.cqcet.entity;

import lombok.Data;

/**
 * Created by �Ǹ�˭ on 2018/10/14.
 */
@Data
public class UploadInfo {
    private String id;			// ����
    private String type;		// ���ͣ���ţ�ƣ�
    private String domain;		// ����
    private String ak;			// ak
    private String sk;			// sk
    private String bucket;		// �ռ�����
    private String compress;	// ѹ����
}
