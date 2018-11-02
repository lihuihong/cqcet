package com.cqcet.entity;

import com.cqcet.enums.OrderStatusEnum;
import lombok.Data;

import java.util.Date;

/**
 * Created by 那个谁 on 2018/11/2.
 */
@Data
public class ShopOrder {
    private String id;
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
    private Integer status = OrderStatusEnum.WAITPAY.getCode();
    private int uid;
}
