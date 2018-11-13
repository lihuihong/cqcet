package com.cqcet.controller.admin;

import com.cqcet.dao.ShopOrderMapper;
import com.cqcet.entity.ShopOrder;
import com.cqcet.services.ShopOrderItemService;
import com.cqcet.services.ShopOrderService;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 那个谁 on 2018/11/8.
 */
@Controller
@RequestMapping("/admin")
public class ShopOrderController {

    @Autowired
    ShopOrderService shopOrderService;
    @Autowired
    ShopOrderItemService shopOrderItemService;

    //订单详情
    @RequestMapping("shop/shopOrder.action")
    public String list(ModelMap map,
                       @RequestParam(required = false, value = "startDate") String startDate,
                       @RequestParam(required = false, value = "endDate") String endDate,
                       @RequestParam(required = false, value = "keyWord") String keyWord,
                       @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                       @RequestParam(value = "pageSize", defaultValue = "5") int pageSize){
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("startDate", startDate);
        param.put("endDate", endDate);
        if (!StringUtils.isEmpty(keyWord)) {
            param.put("keyWord", "%" + keyWord.trim() + "%");
        }
        PageMethod.startPage(pageNum, pageSize);
        List<ShopOrder> os= shopOrderService.list();
        shopOrderItemService.fill(os);
        PageInfo<ShopOrder> pageInfo = new PageInfo<>(os);
        map.put("pageInfo", pageInfo);

        return "admin/shop/list_order";
    }

    @RequestMapping("shop/order_delivery")
    public String delivery(ShopOrder o) throws IOException {
        o.setDeliveryDate(new Date());
        o.setStatus(ShopOrderMapper.waitConfirm);
        shopOrderService.update(o);
        return "redirect:shop/shopOrder.action";
    }
}
