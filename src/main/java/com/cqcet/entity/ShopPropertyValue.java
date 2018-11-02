package com.cqcet.entity;

import lombok.Data;

/**
 * 商品属性值
 * Created by 那个谁 on 2018/10/31.
 */
@Data
public class ShopPropertyValue {
    private String id;
    //外键pid 指向商品表的id字段
    private String pid;
    //外键ptid 指向属性表的id字段
    private String ptid;
    //属性值
    private String value;

    @Override
    public String toString() {
        return "ShopPropertyValueMapper{" +
                "id=" + id +
                ", pid=" + pid +
                ", ptid=" + ptid +
                ", value='" + value + '\'' +
                '}';
    }
}
