package com.cqcet.entity;

import lombok.Data;

/**
 * 商品属性
 * Created by 那个谁 on 2018/10/31.
 */
@Data
public class ShopProperty {
    private String id;
    //外键cid，指向分类表的id字段
    private Integer cid;
    //属性名称
    private String name;


    @Override
    public String toString() {
        return "ShopProperty{" +
                "id=" + id +
                ", cid=" + cid +
                ", name='" + name + '\'' +
                '}';
    }
}
