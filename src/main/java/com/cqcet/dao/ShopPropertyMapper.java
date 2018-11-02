package com.cqcet.dao;

import com.cqcet.entity.ShopProperty;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品属性
 * Created by 那个谁 on 2018/10/31.
 */
public interface ShopPropertyMapper {
    //查询该商品下详情图片
    List<ShopProperty> listByShopId(@Param("cid")int cid);

    //判断商品名是否重复
    int countByName(String name);

    //插入该商品的商品属性
    void insert(ShopProperty shopProperty);

    //更新商品属性
    void update(ShopProperty shopProperty);

    //根据商品属性名称查询id
    ShopProperty idByName(String name);

    //根据id删除商品属性名称
    void delete(Integer id);
}
