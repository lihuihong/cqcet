package com.cqcet.controller.admin;

import com.cqcet.dao.ShopProductImageMapper;
import com.cqcet.entity.*;
import com.cqcet.exception.LException;
import com.cqcet.services.*;
import com.cqcet.util.BASE64DecodedMultipartFile;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.*;
import com.qiniu.util.Base64;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;


/**
 * Created by 那个谁 on 2018/10/30.
 */
@Controller
@RequestMapping("/admin")
public class ShopMessageController {
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


    /**
     * 商品列表
     * @param map
     * @param keyWord
     * @return
     */
    @RequestMapping("shop/list.action")
    public String shopList(ModelMap map,
                           @RequestParam(required = false, value = "categoryId") String categoryId,
                           @RequestParam(required = false, value = "startDate") String startDate,
                           @RequestParam(required = false, value = "endDate") String endDate,
                           @RequestParam(required = false, value = "keyWord") String keyWord,
                           @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                           @RequestParam(value = "pageSize", defaultValue = "5") int pageSize){
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("categoryId", categoryId);
        param.put("startDate", startDate);
        param.put("endDate", endDate);
        if (!StringUtils.isEmpty(keyWord)) {
            param.put("keyWord", "%" + keyWord.trim() + "%");
        }

        param.put("status", 0);
        // pageHelper分页插件
        // 只需要在查询之前调用，传入当前页码，以及每一页显示多少条
        PageMethod.startPage(pageNum, pageSize);
        List<ShopMessage> list = shopMessageService.list(param);
        PageInfo<ShopMessage> pageInfo = new PageInfo<>(list);
        map.put("pageInfo", pageInfo);

        // 查询所有商品分类
        map.put("categoryList", shopCategoryService.list());

        map.put("categoryId", categoryId);
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("keyWord", keyWord);

        return "admin/shop/list_normal";
    }

    /**
     * 商品详情图片
     * @param map
     * @param productImageId
     * @return
     */
    @RequestMapping("shop/productImage_detail.action")
    public String productImage_detail(ModelMap map,
                               @RequestParam(required = false, value = "productImageId") int productImageId){
        if (productImageId != 0){
            List<ShopProductImage> detailList = shopProductImageService.listByPid(productImageId,ShopProductImageMapper.type_detail);
            map.put("detailList",detailList);
        }
        map.put("productImageId",productImageId);
        return "admin/shop/editDetailImage";
    }
    /**
     * 商品单个图片
     * @param map
     * @param productImageId
     * @return
     */
    @RequestMapping("shop/productImage_single.action")
    public String productImage_single(ModelMap map,
                                      @RequestParam(required = false, value = "productImageId") int productImageId){
        if (productImageId != 0){
            List<ShopProductImage> singleList = shopProductImageService.listByPid(productImageId,ShopProductImageMapper.type_single);
            map.put("singleList",singleList);
        }
        map.put("productImageId",productImageId);
        return "admin/shop/editSingleImage";
    }

    /**
     * 编辑商品信息
     * @param map
     * @param id
     * @return
     */
    @RequestMapping("shop/edit.action")
    public String shopEdit(ModelMap map,
                           @RequestParam(required = false,value = "id") String id) throws LException {
        //查询单个商品信息
        if (!StringUtils.isEmpty(id)){
            map.put("shopMessageList",shopMessageService.selectById(Integer.parseInt(id)));
        }
        // 查询所有商品分类
        map.put("categoryList", shopCategoryService.list());
        map.put("user",userService.list_article());
        return "admin/shop/edit";
    }
    @RequestMapping("shop/category_edit.action")
    public String categoryEdit(ModelMap map){
        // 查询所有商品分类
        map.put("categoryList", shopCategoryService.list());
        return "admin/shop/category_list";
    }

    /**
     * 保存商品分类
     * @param idArr
     * @param nameArr
     * @return
     * @throws LException
     */
    @RequestMapping("shop/shop_category_save.json")
    @ResponseBody
    public Result shopCategorySave(@RequestParam(value="idArr") String[] idArr,
                                   @RequestParam(value="nameArr") String[] nameArr) throws LException {

        List<ShopCategory> param = new ArrayList<>();
        for (int i = 0; i < idArr.length; i++) {
            ShopCategory category = new ShopCategory();
            category.setId(idArr[i]);
            category.setName(nameArr[i]);
            param.add(category);
        }
        shopCategoryService.save(param);
        return Result.success();
    }

    /**
     * 删除商品分类
     * @param idArr
     * @return
     * @throws LException
     */
    @RequestMapping("shop/shop_category_delete.json")
    @ResponseBody
    public Result shopCategoryDelete(@RequestParam(value="idArr") String[] idArr) throws LException {

        for (int i = 0; i < idArr.length; i++) {
            shopCategoryService.delete(idArr[i]);
        }
        return Result.success();
    }

    /**
     * 批量移动商品分类
     * @param idArr
     * @param categoryId
     * @return
     */
    @RequestMapping(value = "shop/move.json ", produces="application/json;charset=UTF-8;")
    @ResponseBody
    public Result move(
            @RequestParam(value = "idArr") String[] idArr,
            @RequestParam(value = "categoryId") String categoryId) throws LException {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("idArr", idArr);
        param.put("categoryId", categoryId);

        shopMessageService.bacthUpdate(param);

        return Result.success();
    }

    /**
     * 批量删除商品
     * @param idArr
     * @return
     */
    @RequestMapping(value = "shop/bacth_delete.json")
    @ResponseBody
    public Result bacthDelete(
            @RequestParam(value = "idArr") String[] idArr) throws LException {
        shopMessageService.batchDelete(idArr);
        return Result.success();
    }

    /**
     * 商品属性设置
     * @param map
     * @param categoryId
     * @return
     */
    @RequestMapping("shop/property.action")
    public String property(ModelMap map,
                           @RequestParam(required = false,value = "categoryId") String categoryId,
                           @RequestParam(required = false,value = "shopId") String shopId ){
        List<ShopProperty> shopProperties = shopPropertyService.listByShopId(Integer.valueOf(categoryId));
        List<Map<String,Object>> params = new ArrayList<>();
        for (ShopProperty shopProperty : shopProperties) {
            Map<String,Object> param = new HashMap<>();
            ShopPropertyValue shopPropertyValue = shopPropertyValueService.listById(Integer.parseInt(shopId), Integer.parseInt(shopProperty.getId()));
            param.put("shopPropertyValue",shopPropertyValue);
            param.put("shopProperty",shopProperty);
            params.add(param);
        }

        map.put("shopPropertyList",params);
        map.put("categoryName",shopCategoryService.selectById(Integer.parseInt(categoryId)));
        map.put("categoryId",categoryId);
        map.put("shopId",shopId);
        return "admin/shop/property_list";

    }

    /**
     * 保存商品属性名称以及属性值
     * @param idArr
     * @param valueArr
     * @param nameArr
     * @param categoryId
     * @param shopId
     * @return
     * @throws LException
     */
    @RequestMapping("shop/shopProperty_save.json")
    @ResponseBody
    public Result shopPropertySave(@RequestParam(value="idArr") String[] idArr,
                                   @RequestParam(value="valueArr") String[] valueArr,
                                   @RequestParam(value="nameArr") String[] nameArr,
                                   @RequestParam(value="categoryId") String categoryId,
                                   @RequestParam(value="shopId") String shopId) throws LException {

        List<ShopPropertyValue> shopPropertyValues = new ArrayList<>();
        for (int i = 0; i < idArr.length; i++) {
            //设置商品属性名称
            ShopProperty shopProperty = new ShopProperty();
            shopProperty.setId(idArr[i]);
            shopProperty.setName(nameArr[i]);
            shopProperty.setCid(Integer.valueOf(categoryId));
            shopPropertyService.save(shopProperty);
            //设置商品属性名称对应的属性值
            ShopPropertyValue shopPropertyValue = new ShopPropertyValue();
            shopPropertyValue.setId(idArr[i]);

            if (!StringUtils.isEmpty(shopProperty.getId())){
                shopPropertyValue.setPtid(String.valueOf(shopProperty.getId()));
            }
            shopPropertyValue.setValue(valueArr[i]);
            shopPropertyValue.setPid(shopId);
            shopPropertyValues.add(shopPropertyValue);
        }
        shopPropertyValueService.save(shopPropertyValues);
        return Result.success();
    }

    /**
     * 删除商品属性名称及属性值
     * @param idArr
     * @return
     */
    @RequestMapping("shop/shopProperty_delete.json")
    @ResponseBody
    public Result shopPropertyDelete(@RequestParam(value="idArr") String[] idArr){
        for (int i = 0; i < idArr.length; i++) {
            shopPropertyValueService.delete(Integer.valueOf(idArr[i]));
            shopPropertyService.delete(Integer.valueOf(idArr[i]));
        }
        return Result.success();
    }

    /**
     * 保存商品信息
     * @param shopMessage
     * @return
     */
    @RequestMapping("shop/save.json")
    @ResponseBody
    public Result save(ShopMessage shopMessage) throws LException {
        shopMessageService.save(shopMessage);
        return Result.success();
    }

    /**
     * 保存上传的商品详情图片
     * @param imageUrl
     * @param pid
     * @return
     */
    @RequestMapping("shop/image_save_detail.json")
    @ResponseBody
    public Result image_save_detail(@RequestParam(value = "imageUrl") String[] imageUrl,
                             @RequestParam(value = "pid") String pid){
        ShopProductImage shopProductImage = new ShopProductImage();
        for (int i = 0; i < imageUrl.length; i++) {
            shopProductImage.setValue(imageUrl[i]);
            shopProductImage.setPid(Integer.valueOf(pid));
            shopProductImage.setType(ShopProductImageMapper.type_detail);
            shopProductImageService.add(shopProductImage);
        }
        return Result.success();
    }

    /**
     * 保存上传的商品当个图片
     * @param imageUrl
     * @param pid
     * @return
     */
    @RequestMapping("shop/image_save_single.json")
    @ResponseBody
    public Result image_save_single(@RequestParam(value = "imageUrl") String[] imageUrl,
                             @RequestParam(value = "pid") String pid){
        ShopProductImage shopProductImage = new ShopProductImage();
        for (int i = 0; i < imageUrl.length; i++) {
            shopProductImage.setValue(imageUrl[i]);
            shopProductImage.setPid(Integer.valueOf(pid));
            shopProductImage.setType(ShopProductImageMapper.type_single);
            shopProductImageService.add(shopProductImage);
        }
        return Result.success();
    }

    /**
     * 保存上传的商品图片
     * @param imageUrl
     * @return
     */
    @RequestMapping("shop/image_delete.json")
    @ResponseBody
    public Result image_delete(@RequestParam(value = "imageUrl") String imageUrl){
            shopProductImageService.deleteByValue(imageUrl);
            return Result.success();
    }

    /**
     * 上传文件到七牛云
     *
     * @throws IOException
     */
    @RequestMapping("shop/upload.json")
    @ResponseBody
    public Result upload(@RequestParam String image) throws IOException {
        MultipartFile file = BASE64DecodedMultipartFile.base64ToMultipart(image);
        /**
         * 构造一个带指定Zone对象的配置类 华东 : Zone.zone0() 华北 : Zone.zone1() 华南 : Zone.zone2() 北美 :
         * Zone.zoneNa0()
         */
        Configuration cfg = new Configuration(Zone.zone0());
        // ...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        // ...生成上传凭证，然后准备上传
        String accessKey = "usvmdc4mAZAL3bhwLkm5iMk50aq-31Dl_U8b_P5j";
        String secretKey = "VI2N4hgyhNO7QrJcGFARIL70A6S6rNaoWD6m-lM6";
        String bucket = "heylhh";
        // 默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;

        String imgUrl = "";
        try {
            // 数据流上传
            InputStream byteInputStream = file.getInputStream();
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(byteInputStream, key, upToken, null, null);
                // 解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
                imgUrl = putRet.hash;
                System.err.println("http://heylhh.com/" + imgUrl);
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    // ignore
                }
            }
        } catch (UnsupportedEncodingException ex) {
            // ignore
        }

        return Result.success().add("imgUrl", "http://heylhh.com/" + imgUrl);
    }
}
