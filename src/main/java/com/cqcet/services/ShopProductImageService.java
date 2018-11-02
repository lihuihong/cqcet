package com.cqcet.services;

import com.cqcet.dao.ShopProductImageMapper;
import com.cqcet.entity.ShopProductImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 那个谁 on 2018/10/30.
 */
@Service
public class ShopProductImageService {

    @Autowired
    private ShopProductImageMapper shopProductImageMapper;

    public ShopProductImage listByproductImageId(int id){
        return shopProductImageMapper.listByproductImageId(id);
    }

    public ShopProductImage save(ShopProductImage shopProductImage){
        return shopProductImageMapper.update(shopProductImage);
    }
}
