package com.cqcet.entity;

import lombok.Data;

/**
 * 商品详情图片
 * Created by 那个谁 on 2018/10/30.
 */
@Data
public class ShopProductImage {
    private Integer id;
    private Integer pid;
    private String value1;
    private String value2;
    private String value3;
    private String value4;

    @Override
    public String toString() {
        return "ShopProductImage{" +
                "id=" + id +
                ", pid=" + pid +
                ", value1='" + value1 + '\'' +
                ", value2='" + value2 + '\'' +
                ", value3='" + value3 + '\'' +
                ", value4='" + value4 + '\'' +
                '}';
    }
}
