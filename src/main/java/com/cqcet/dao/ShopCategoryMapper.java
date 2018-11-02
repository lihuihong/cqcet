package com.cqcet.dao;

import com.cqcet.entity.ShopCategory;

import java.util.List;


/**
 * 商品分类
 * Created by 那个谁 on 2018/10/30.
 */
public interface ShopCategoryMapper {
    //根据商品id查询商品分类
    ShopCategory selectById(int id);
    //查询所有分类
    List<ShopCategory> list();


    void insert(ShopCategory category);

    void update(ShopCategory category);

    int countByName(String name);

    void delete(String id);
}
