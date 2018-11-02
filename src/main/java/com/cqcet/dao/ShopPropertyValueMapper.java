package com.cqcet.dao;

import com.cqcet.entity.ShopPropertyValue;
import org.apache.ibatis.annotations.Param;

/**
 * 商品属性值
 * Created by 那个谁 on 2018/10/31.
 */
public interface ShopPropertyValueMapper {

    ShopPropertyValue listById(@Param("pid") int pid,@Param("ptid") int ptid);

    void insert(ShopPropertyValue shopPropertyValue);

    void update(ShopPropertyValue shopPropertyValue);

    void delete(int ptid);
}
