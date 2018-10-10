package com.cqcet.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Ьћзг
 */
@Data
public class Article implements Serializable {
    private Integer id;

    private String title;

    private String content;

    private String cover;

    private Integer viewCount;

    private Date updateTime;

    private String status;

    private Integer typeId;

    private Integer userId;

    private String name;

    private String username;

    private String collegeId;


    private String avatar;



    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", content=").append(content);
        sb.append(", cover=").append(cover);
        sb.append(", viewCount=").append(viewCount);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", status=").append(status);
        sb.append(", typeId=").append(typeId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}