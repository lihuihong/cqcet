package com.cqcet.services;

import com.cqcet.dao.ShopPropertyValueMapper;
import com.cqcet.entity.ShopPropertyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by 那个谁 on 2018/10/31.
 */
@Service
public class ShopPropertyValueService {
    @Autowired
    private ShopPropertyValueMapper shopPropertyValueMapper;

    public ShopPropertyValue listById(int pit, int ptid){
        return shopPropertyValueMapper.listById(pit,ptid);
    }

    //保存商品属性值
    public void save(List<ShopPropertyValue> shopPropertyValues) {
        for (ShopPropertyValue shopPropertyValue : shopPropertyValues) {
            //判断是更新还是插入
            if (StringUtils.isEmpty(shopPropertyValue.getId())){
                //插入
                shopPropertyValueMapper.insert(shopPropertyValue);
            }else {
                //更新
                shopPropertyValueMapper.update(shopPropertyValue);
            }
        }
    }

    //根据商品属性id删除商品属性值
    public void delete(int ptid) {
        shopPropertyValueMapper.delete(ptid);
    }
}
