package com.cqcet.services;

import com.cqcet.dao.ShopCategoryMapper;
import com.cqcet.entity.ShopCategory;
import com.cqcet.exception.LException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by 那个谁 on 2018/10/30.
 */
@Service
public class ShopCategoryService {

    @Autowired
    public ShopCategoryMapper shopCategoryMapper;

    /**
     * 根据商品id查询商品分类
     * @param id
     * @return
     */
    public ShopCategory selectById(int id){
        return shopCategoryMapper.selectById(id);
    }

    /**
     * 查询所有商品分类
     * @return
     */
    public List<ShopCategory> list(){
        return shopCategoryMapper.list();
    }


    //保存商品分类
    public void save(List<ShopCategory> shopCategoryList) throws LException {
        for (ShopCategory category : shopCategoryList) {
            if (StringUtils.isEmpty(category.getId())){
                int count = shopCategoryMapper.countByName(category.getName());
                if (count > 0 ){
                    throw new LException("该商品分类已存在！");
                }
                //插入
                shopCategoryMapper.insert(category);
            }else {
                //更新
                shopCategoryMapper.update(category);
            }
        }

    }
    //删除商品分类
    public void delete(String id) throws LException {
        try {
            shopCategoryMapper.delete(id);
        }catch (Exception e){
            throw new LException("该商品分类被占用，不能删除！");
        }

    }
}
