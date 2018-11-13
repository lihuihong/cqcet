package com.cqcet.services;

import com.cqcet.dao.ShopMessageMapper;
import com.cqcet.dao.ShopOrderItemMapper;
import com.cqcet.dao.ShopOrderMapper;
import com.cqcet.dao.UserMapper;
import com.cqcet.entity.ShopMessage;
import com.cqcet.entity.ShopOrder;
import com.cqcet.entity.ShopOrderItem;
import com.cqcet.entity.ShopOrderItemExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 那个谁 on 2018/11/6.
 */
@Service
public class ShopOrderItemService {

    @Autowired
    ShopOrderItemMapper shopOrderItemMapper;
    @Autowired
    ShopMessageMapper shopMessageMapper;
    @Autowired
    UserMapper userMapper;



    public void add(ShopOrderItem c) {
        shopOrderItemMapper.insert(c);
    }

    public void delete(int id) {
        shopOrderItemMapper.deleteByPrimaryKey(id);
    }

    public void update(ShopOrderItem c) {
        shopOrderItemMapper.updateByPrimaryKeySelective(c);
    }

    public ShopOrderItem get(int id) {
        ShopOrderItem result = shopOrderItemMapper.selectByPrimaryKey(id);
        setProduct(result);
        return result;
    }

    public List<ShopOrderItem> list(){
        ShopOrderItemExample example =new ShopOrderItemExample();
        example.setOrderByClause("id desc");
        return shopOrderItemMapper.selectByExample(example);

    }

    public void fill(List<ShopOrder> os) {
        for (ShopOrder o : os) {
            fill(o);
        }
    }

    public int getSaleCount(int pid) {
        ShopOrderItemExample example =new ShopOrderItemExample();
        example.createCriteria().andPidEqualTo(pid);
        List<ShopOrderItem> ois =shopOrderItemMapper.selectByExample(example);
        int result =0;
        for (ShopOrderItem oi : ois) {
            result+=oi.getNumber();
        }
        return result;
    }

    public List<ShopOrderItem> listByUser(int uid) {
        ShopOrderItemExample example =new ShopOrderItemExample();
        example.createCriteria().andUidEqualTo(uid).andOidIsNull();
        List<ShopOrderItem> result =shopOrderItemMapper.selectByExample(example);
        setProduct(result);
        return result;
    }

    public void fill(ShopOrder o) {
        ShopOrderItemExample example =new ShopOrderItemExample();
        example.createCriteria().andOidEqualTo(o.getId());
        example.setOrderByClause("id desc");
        List<ShopOrderItem> ois =shopOrderItemMapper.selectByExample(example);
        setProduct(ois);

        float total = 0;
        int totalNumber = 0;
        for (ShopOrderItem oi : ois) {
            total+=oi.getNumber()*oi.getShopMessage().getPromotePrice();
            totalNumber+=oi.getNumber();

        }
        o.setUser(userMapper.selectById(String.valueOf(o.getUid())));
        o.setTotal(total);
        o.setTotalNumber(totalNumber);
        o.setOrderItems(ois);


    }

    public void setProduct(List<ShopOrderItem> ois){
        for (ShopOrderItem oi: ois) {
            setProduct(oi);
        }
    }

    private void setProduct(ShopOrderItem oi) {
        ShopMessage p = shopMessageMapper.selectById(oi.getPid());
        oi.setShopMessage(p);
    }
}
