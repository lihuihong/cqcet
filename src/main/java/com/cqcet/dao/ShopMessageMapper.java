package com.cqcet.dao;

import com.cqcet.entity.ShopMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 商品
 * Created by 那个谁 on 2018/10/30.
 */
public interface ShopMessageMapper {

    //查询所有商品列表
    List<ShopMessage> list(Map<String, Object> param);

    //添加商品
    int insert(ShopMessage shopMessage);

    //更新商品
    int update(ShopMessage shopMessage);

    //根据商品id查询商品信息
    ShopMessage selectById(int id);

    //批量移动商品分类
    void bacthUpdate(Map<String, Object> param);

    //根据商品主键删除商品
    void batchDelete(@Param("idArr") String[] idArr);
}
