package com.cqcet.services;

import com.cqcet.dao.ShopOrderItemMapper;
import com.cqcet.dao.ShopOrderMapper;
import com.cqcet.entity.ShopOrder;
import com.cqcet.entity.ShopOrderExample;
import com.cqcet.entity.ShopOrderItem;
import com.cqcet.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
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
    @Autowired
    UserService userService;

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
    //删除
    public void delete(int id){
        shopOrderMapper.deleteByPrimaryKey(id);
    }
    //更新
    public void update(ShopOrder shopOrder){
        shopOrderMapper.updateByPrimaryKeySelective(shopOrder);
    }
    //得到
    public ShopOrder get(int id){
        return shopOrderMapper.selectByPrimaryKey(id);
    }
    //列表

    public List<ShopOrder> list(){
        ShopOrderExample example =new ShopOrderExample();
        example.setOrderByClause("id desc");
        return shopOrderMapper.selectByExample(example);

    }

    public List list(int uid, String excludedStatus) {
        ShopOrderExample example =new ShopOrderExample();
        example.createCriteria().andUidEqualTo(uid).andStatusNotEqualTo(excludedStatus);
        example.setOrderByClause("id desc");
        return shopOrderMapper.selectByExample(example);
    }

    public void setUser(List<ShopOrder> os){
        for (ShopOrder o : os)
            setUser(o);
    }
    public void setUser(ShopOrder o){
        int uid = o.getUid();
        User u = userService.selectById(String.valueOf(uid));
        o.setUser(u);
    }

}
