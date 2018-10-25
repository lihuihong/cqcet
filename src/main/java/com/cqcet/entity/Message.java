package com.cqcet.entity;

import lombok.Data;

import java.util.Date;

/**
 * վ����
 * Created by �Ǹ�˭ on 2018/10/25.
 */
@Data
public class Message {
    private int id;
    private int fromId;
    private int toId;
    private String content;
    private Date createdDate;
    private int hasRead;
    private String conversationId;
}
