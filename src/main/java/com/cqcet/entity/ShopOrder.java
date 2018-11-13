package com.cqcet.entity;

import com.cqcet.dao.ShopOrderMapper;
import com.cqcet.enums.OrderStatusEnum;
import com.cqcet.services.ShopOrderService;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 用户订单
 * Created by 那个谁 on 2018/11/2.
 */
@Data
public class ShopOrder {
    private Integer id;

    private String orderCode;

    private String address;

    private String post;

    private String receiver;

    private String mobile;

    private String userMessage;

    private Date createDate;

    private Date payDate;

    private Date deliveryDate;

    private Date confirmDate;

    private Integer uid;

    private String status;


    /*如下是非数据库字段*/
    private List<ShopOrderItem> orderItems;

    private User user;

    private float total;

    private int totalNumber;

    public String getStatusDesc(){

        String desc ="未知";
        switch(status){
            case ShopOrderMapper.waitPay:
                desc="待付款";
                break;
            case ShopOrderMapper.waitDelivery:
                desc="待发货";
                break;
            case ShopOrderMapper.waitConfirm:
                desc="待收货";
                break;
            case ShopOrderMapper.waitReview:
                desc="等评价";
                break;
            case ShopOrderMapper.finish:
                desc="完成";
                break;
            case ShopOrderMapper.delete:
                desc="刪除";
                break;
            default:
                desc="未知";
        }
        return desc;
    }

}
