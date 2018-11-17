package com.cqcet.controller.shop;

import com.cqcet.dao.ShopProductImageMapper;
import com.cqcet.entity.*;
import com.cqcet.exception.LException;
import com.cqcet.services.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.*;

/**
 * Created by 那个谁 on 2018/9/14.
 * 帖子
 */
@Controller
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    private ShopMessageService shopMessageService;
    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private ShopProductImageService shopProductImageService;
    @Autowired
    private UserService userService;
    @Autowired
    private ShopPropertyService shopPropertyService;
    @Autowired
    private ShopPropertyValueService shopPropertyValueService;

    @Autowired
    private ShopOrderItemService shopOrderItemService;
    @Autowired
    private ReviewService reviewService;


    /**
     * 商品列表
     * @param map
     * @param keyWord
     * @return
     */
    @RequestMapping("shop/list.action")
    public String shopList(ModelMap map,
                           @RequestParam(required = false, value = "keyWord") String keyWord){
        Map<String, Object> param = new HashMap<String, Object>();
        if (!StringUtils.isEmpty(keyWord)) {
            param.put("keyWord", "%" + keyWord.trim() + "%");
        }
        param.put("status", 0);
        List<ShopCategory> shopCategoryList = shopCategoryService.list();
        List<List> lists = new ArrayList<>();
        for (ShopCategory category : shopCategoryList) {
            List<Object> shopMessageList = new ArrayList<>();
            param.put("categoryId",category.getId());
            List<ShopMessage> list = shopMessageService.list(param);
            shopMessageList.add(category);
            shopMessageList.add(list);
            lists.add(shopMessageList);
        }
        map.put("lists", lists);
        // 查询所有商品分类
        map.put("categoryList", shopCategoryList);
        map.put("keyWord", keyWord);
        return "/shop/shophome";
    }
    /**
     * 商品分类更多
     * @param map
     * @param id
     * @return
     */
    @RequestMapping("/shop/forecategory.action")
    public String shopEdit(ModelMap map,
                           @RequestParam(required = false,value = "id") String id,
                           @RequestParam(required = false,value = "sort")String sort,
                           @RequestParam(required = false,value = "categoryName")String categoryName) throws LException {

        Map<String, Object> param = new HashMap<String, Object>();
        //查询单个商品信息
        if (!StringUtils.isEmpty(id) && !StringUtils.isEmpty(categoryName)){
            List<ShopMessage> shopMessageList = shopMessageService.byCategoryId(Integer.parseInt(id));
            List<List> lists = new ArrayList<>();
            for (ShopMessage shopMessage : shopMessageList) {
                List<Object> categoryList = new ArrayList<>();
                User user = userService.selectById(String.valueOf(shopMessage.getUserId()));
                categoryList.add(user);
                categoryList.add(shopMessage);
                //商品成交订单
                int saleCount = shopOrderItemService.getSaleCount(shopMessage.getId());
                categoryList.add(saleCount);
                //商品评价条数
                int count = reviewService.getCount(shopMessage.getId());
                categoryList.add(count);
                lists.add(categoryList);
            }
            map.put("lists",lists);
            map.put("categoryName",categoryName);
            List<ShopCategory> shopCategoryList = shopCategoryService.list();
            // 查询所有商品分类
            map.put("categoryList", shopCategoryList);
        }
        return "/shop/forecategory";
    }

    /**
     * 商品详情
     * @param map
     * @param pid
     * @return
     * @throws LException
     */
    @RequestMapping("/shop/foreproduct.action")
    public String foreproduct(ModelMap map,
                              @RequestParam(required = false,value = "pid") String pid) throws LException {
        //获取商品详情
        ShopMessage shopMessage = shopMessageService.selectById(Integer.parseInt(pid));
        //获取商品对应得图片
        List<ShopProductImage> singleList = shopProductImageService.listByPid(shopMessage.getId(), ShopProductImageMapper.type_single);
        List<ShopProductImage> detailList = shopProductImageService.listByPid(shopMessage.getId(),ShopProductImageMapper.type_detail);
        //获取商品评论
        List<Review> reviews = reviewService.list(shopMessage.getId());
        //获取商品属性以及属性值
        List<ShopProperty> shopPropertyList = shopPropertyService.listByShopId(shopMessage.getId());
        List<Map<String,Object>> params = new ArrayList<>();
        for (ShopProperty shopProperty : shopPropertyList) {
            Map<String,Object> param = new HashMap<>();
            ShopPropertyValue shopPropertyValue = shopPropertyValueService.listById(shopMessage.getId(), Integer.parseInt(shopProperty.getId()));
            param.put("shopPropertyValue",shopPropertyValue);
            param.put("shopProperty",shopProperty);
            params.add(param);
        }
        //商品成交订单
        int saleCount = shopOrderItemService.getSaleCount(shopMessage.getId());
        //商品评价条数
        int count = reviewService.getCount(shopMessage.getId());
        map.put("pvs",params);
        map.put("p",shopMessage);
        map.put("singleList",singleList);
        map.put("detailList",detailList);
        map.put("reviews",reviews);
        map.put("saleCount",saleCount);
        map.put("count",count);
        return "/shop/foreproduct";
    }

}
