package com.cqcet.dao;

import com.cqcet.entity.ShopOrderItem;
import com.cqcet.entity.ShopOrderItemExample;

import java.util.List;


/**
 * Created by 那个谁 on 2018/11/6.
 */
public interface ShopOrderItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShopOrderItem record);

    int insertSelective(ShopOrderItem record);

    List<ShopOrderItem> selectByExample(ShopOrderItemExample example);

    ShopOrderItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShopOrderItem record);

    int updateByPrimaryKey(ShopOrderItem record);
}
