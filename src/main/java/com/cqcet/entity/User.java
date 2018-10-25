package com.cqcet.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    private String id;

    private String username;

    private String password;

    private String level;

    private String email;

    private String phone;

    private String status;

    private String studentId;

    private String college;

    private Date registerTime;

    private Date lastLoginTime;

    private static final long serialVersionUID = 1L;

    private String avatar;
    private String groupId;
    private String groupName;
    private String professionalName;
    private String professionalId;
    private String collegeIdName;


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", level=").append(level);
        sb.append(", email=").append(email);
        sb.append(", phone=").append(phone);
        sb.append(", status=").append(status);
        sb.append(", studentId=").append(studentId);
        sb.append(", college=").append(college);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}