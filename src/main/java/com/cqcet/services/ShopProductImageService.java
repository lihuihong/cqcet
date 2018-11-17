package com.cqcet.services;

import com.cqcet.dao.ShopProductImageMapper;
import com.cqcet.entity.ProductImageExample;
import com.cqcet.entity.ShopProductImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 那个谁 on 2018/10/30.
 */
@Service
public class ShopProductImageService {

    @Autowired
    private ShopProductImageMapper shopProductImageMapper;

    public void add(ShopProductImage pi) {
        shopProductImageMapper.insert(pi);
    }

    public void delete(int id) {
        shopProductImageMapper.deleteByPrimaryKey(id);
    }

    public void update(ShopProductImage pi) {
        shopProductImageMapper.updateByPrimaryKeySelective(pi);

    }

    public ShopProductImage get(int id) {
        return shopProductImageMapper.selectByPrimaryKey(id);
    }

    public List<ShopProductImage> list(int pid, String type) {
        ProductImageExample example =new ProductImageExample();
        example.createCriteria()
                .andPidEqualTo(pid)
                .andTypeEqualTo(type);
        example.setOrderByClause("id desc");
        return shopProductImageMapper.selectByExample(example);
    }

    public void deleteByValue(String imageUrl) {
        shopProductImageMapper.deleteByValue(imageUrl);
    }

    public List<ShopProductImage> listByPid(int productImageId,String type) {
        return shopProductImageMapper.listByPid(productImageId,type);
    }
}
