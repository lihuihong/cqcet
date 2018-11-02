package com.cqcet.entity;

import lombok.Data;

/**
 * 商品分类
 * Created by 那个谁 on 2018/10/30.
 */
@Data
public class ShopCategory {
    private String id;
    private String name;

    @Override
    public String toString() {
        return "ShopCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
