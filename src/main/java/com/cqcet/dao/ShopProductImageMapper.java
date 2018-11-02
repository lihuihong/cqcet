package com.cqcet.dao;

import com.cqcet.entity.ShopProductImage;


/**
 * 商品下详情图片
 * Created by 那个谁 on 2018/10/30.
 */
public interface ShopProductImageMapper {

    //查询该商品下详情图片
    ShopProductImage listByproductImageId(int pid);

    ShopProductImage update(ShopProductImage shopProductImage);
}
