package com.cqcet.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by 那个谁 on 2018/10/25.
 */
@Data
public class Message {
    private int id;
    private String fromId;
    private String toId;
    private String content;
    private Date createdDate;
    private String hasRead;
    private String conversationId;

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", fromId=" + fromId +
                ", toId=" + toId +
                ", content='" + content + '\'' +
                ", createdDate=" + createdDate +
                ", hasRead=" + hasRead +
                ", conversationId='" + conversationId + '\'' +
                '}';
    }
}
