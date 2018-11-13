package com.cqcet.services;

import com.cqcet.dao.ShopMessageMapper;
import com.cqcet.entity.Article;
import com.cqcet.entity.ShopMessage;
import com.cqcet.exception.LException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 商品
 * Created by 那个谁 on 2018/10/30.
 */
@Service
public class ShopMessageService {
    @Autowired
    ShopMessageMapper shopMessageMapper;

    /**
     * 查询所有商品列表
     * @param param
     * @return
     */
    public List<ShopMessage> list(Map<String, Object> param){
        return shopMessageMapper.list(param);
    }

    /**
     * 保存商品信息
     * @param shopMessage
     * @return
     */
    public void save(ShopMessage shopMessage) throws LException {
        Date currentTime = new Date();
        // 判断是新增还是更新
        if (StringUtils.isEmpty(shopMessage.getId()) || shopMessage.getUserId()==0 || String.valueOf(shopMessage.getId()) == "null") {
            // 新增
            shopMessage.setStatus(0);
            shopMessage.setUpdateTime(currentTime);
            try {
                shopMessageMapper.insert(shopMessage);

            }catch (Exception e){
                throw new LException("编辑内容或标题太长了");
            }
        } else {
            // 更新
            try {
                shopMessage.setUpdateTime(currentTime);
                shopMessageMapper.update(shopMessage);
            }catch (Exception e){
                throw new LException("编辑内容或标题太长了");
            }

        }

    }

    /**
     * 根据商品id查询商品信息
     * @param id
     * @return
     */
    public ShopMessage selectById(int id)  throws LException{
            return shopMessageMapper.selectById(id);
    }

    /**
     * 批量移动商品分类
     * @param param
     */
    public void bacthUpdate(Map<String, Object> param)  throws LException {
        try {
            shopMessageMapper.batchUpdate(param);
        }catch (Exception e){
            throw new LException("批量移动商品分类失败！");
        }
    }

    /**
     * 批量删除商品
     * @param idArr
     * @throws LException
     */
    public void batchDelete(String[] idArr)  throws LException{
        try {
            shopMessageMapper.batchDelete(idArr);
        }catch (Exception e){
            throw new LException("批量删除商品失败！");
        }
    }
}
