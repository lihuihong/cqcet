package com.cqcet.entity;

import lombok.Data;

/**
 * Created by 那个谁 on 2018/10/9.
 */
@Data
public class Forum {
    private String collegeId;
    private String collegeName;
    private String collegeDes;
    private String collegeAvatar;
    private int articleCount;
    private int typeCount;
}
