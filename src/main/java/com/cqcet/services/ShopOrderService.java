package com.cqcet.services;

import com.cqcet.dao.ShopOrderItemMapper;
import com.cqcet.dao.ShopOrderMapper;
import com.cqcet.entity.ShopOrder;
import com.cqcet.entity.ShopOrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单管理
 * Created by 那个谁 on 2018/11/6.
 */
@Service
public class ShopOrderService {
    @Autowired
    ShopOrderMapper shopOrderMapper;
    @Autowired
    ShopOrderItemMapper shopOrderItemMapper;

    //新增订单
    public void add(ShopOrder shopOrder){
        shopOrderMapper.insert(shopOrder);
    }

    public float add(ShopOrder shopOrder,List<ShopOrderItem> shopOrderItems){
        float total = 0;
        add(shopOrder);

        if(false)
            throw new RuntimeException();

        for (ShopOrderItem oi: shopOrderItems) {
            oi.setOid(Integer.valueOf(shopOrder.getId()));
            shopOrderItemMapper.updateByPrimaryKeySelective(oi);
            total+=oi.getShopMessage().getPromotePrice()*oi.getNumber();
        }
        return total;

    }
    public void delete(int id){
        shopOrderMapper.deleteByPrimaryKey(id);
    }
    public void update(ShopOrder shopOrder){
        shopOrderMapper.updateByPrimaryKeySelective(shopOrder);
    }
    public ShopOrder get(int id){
        return shopOrderMapper.selectByPrimaryKey(id);
    }

}
