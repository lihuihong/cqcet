package com.cqcet.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Ìû×Ó·ÖÀà
 */
@Data
public class Type implements Serializable {
    private String id;

    private String name;

    private String sort;

    private static final long serialVersionUID = 1L;


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", sort=").append(sort);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}