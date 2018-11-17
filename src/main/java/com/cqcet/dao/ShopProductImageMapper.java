package com.cqcet.dao;

import com.cqcet.entity.ProductImageExample;
import com.cqcet.entity.ShopProductImage;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 商品下详情图片
 * Created by 那个谁 on 2018/10/30.
 */
public interface ShopProductImageMapper {

    String type_single = "type_single";
    String type_detail = "type_detail";

    int deleteByPrimaryKey(Integer id);

    int insert(ShopProductImage record);

    int insertSelective(ShopProductImage record);

    List<ShopProductImage> selectByExample(ProductImageExample example);

    ShopProductImage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShopProductImage record);

    int updateByPrimaryKey(ShopProductImage record);

    void deleteByValue(String imageUrl);

    List<ShopProductImage> listByPid(@Param("productImageId") int productImageId,@Param("type") String type);
}
