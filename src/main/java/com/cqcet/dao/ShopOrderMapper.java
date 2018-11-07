package com.cqcet.dao;

import com.cqcet.entity.ShopOrder;


/**
 * 订单处理
 * Created by 那个谁 on 2018/11/6.
 */
public interface ShopOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShopOrder record);

    int insertSelective(ShopOrder record);

    //List<Order> selectByExample(OrderExample example);

    ShopOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShopOrder record);

    int updateByPrimaryKey(ShopOrder record);
}
