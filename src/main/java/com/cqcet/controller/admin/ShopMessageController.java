package com.cqcet.controller.admin;

import com.cqcet.entity.*;
import com.cqcet.exception.LException;
import com.cqcet.services.*;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
     * @param categoryId
     * @param startDate
     * @param endDate
     * @param keyWord
     * @param pageNum
     * @param pageSize
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
     * 商品图片
     * @param map
     * @param productImageId
     * @return
     */
    @RequestMapping("shop/productImage.action")
    public String productImage(ModelMap map,
                               @RequestParam(required = false, value = "productImageId") int productImageId){
        if (productImageId != 0){
            ShopProductImage shopProductImage = shopProductImageService.listByproductImageId(productImageId);
            map.put("shopProductImage",shopProductImage);
        }
        return "admin/shop/editImage";
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
     * 保存上传的商品图片
     * @param shopProductImage
     * @return
     */
    @RequestMapping("shop/image_save.json")
    @ResponseBody
    public Result image_save(ShopProductImage shopProductImage){
        shopProductImageService.save(shopProductImage);
        return Result.success();
    }

    /**
     * 上传文件到七牛云
     *
     * @throws IOException
     */
    @RequestMapping("shop/upload.json")
    @ResponseBody
    public Result upload(String file64) throws IOException {

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
        String bucketname = "heylhh";
        Auth auth = Auth.create(accessKey, secretKey);

        // 上传的图片名
        String key = UUID.randomUUID().toString();

        file64 = file64.substring(22);
        System.out.println("file64:"+file64);
        String url = "http://heylhh.com/" + -1 + "/key/" + UrlSafeBase64.encodeToString(key);
        // 非华东空间需要根据注意事项 1 修改上传域名
        RequestBody rb = RequestBody.create(null, file64);
        String upToken  = auth.uploadToken(bucketname, null, 3600, new StringMap().put("insertOnly", 1));
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/octet-stream")
                .addHeader("Authorization", "UpToken " + upToken)
                .post(rb).build();
        System.out.println(request.headers());
        OkHttpClient client = new OkHttpClient();
        okhttp3.Response response = client.newCall(request).execute();
        System.out.println(response);


        return Result.success().add("imgUrl", "http://heylhh.com/" + key);
    }
}
