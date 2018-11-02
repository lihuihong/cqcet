package com.cqcet.services;

import com.cqcet.dao.ShopPropertyMapper;
import com.cqcet.entity.ShopProperty;
import com.cqcet.exception.LException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by 那个谁 on 2018/10/31.
 */
@Service
public class ShopPropertyService {

    @Autowired
    private ShopPropertyMapper shopPropertyMapper;

    //通过商品id查询商品的属性
    public List<ShopProperty> listByShopId(int cid) {
        return shopPropertyMapper.listByShopId(cid);
    }

    // 保存商品的属性名称
    public void save(ShopProperty shopProperty) throws LException {
            //判断是更新还是插入
            if (StringUtils.isEmpty(shopProperty.getId())) {
                //判断商品属性名是否重复
                int count = shopPropertyMapper.countByName(shopProperty.getName());
                if (count > 0){
                    throw new LException("该商品已存在该属性");
                }
                //插入
                shopPropertyMapper.insert(shopProperty);
            }else {
                //更新
                shopPropertyMapper.update(shopProperty);

            }

    }

    //根据商品属性名称查询id
    public ShopProperty idByName(String name){
        return shopPropertyMapper.idByName(name);
    }

    //根据id删除商品属性名称
    public void delete(Integer id) {
        shopPropertyMapper.delete(id);
    }
}
