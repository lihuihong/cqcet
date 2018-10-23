package com.cqcet.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Answer {

    private Integer id;
    private Integer acticleId;
    private Integer childId;
    private Integer parentId;
    private String content;
    private Integer userId;
    private Date updateTime;
}
