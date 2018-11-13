package com.cqcet.dao;

import com.cqcet.entity.ShopOrder;
import com.cqcet.entity.ShopOrderExample;


import java.util.List;


/**
 * 订单处理
 * Created by 那个谁 on 2018/11/6.
 */
public interface ShopOrderMapper {

    String waitPay = "waitPay";
    String waitDelivery = "waitDelivery";
    String waitConfirm = "waitConfirm";
    String waitReview = "waitReview";
    String finish = "finish";
    String delete = "delete";

    int deleteByPrimaryKey(Integer id);

    int insert(ShopOrder record);

    int insertSelective(ShopOrder record);

    List<ShopOrder> selectByExample(ShopOrderExample example);

    ShopOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShopOrder record);

    int updateByPrimaryKey(ShopOrder record);
}
