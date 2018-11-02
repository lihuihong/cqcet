package com.cqcet.enums;

import lombok.Getter;

/**
 * 订单状态
 * Created by 那个谁 on 2018/11/2.
 */
@Getter
public enum  OrderStatusEnum {

    WAITPAY(0,"待付款"),
    WAITDELIVERY(1, "待发货"),
    WAITCONFIRM(2, "待收货"),
    WAITREVIEW(3,"等评价"),
    FINISH(4,"完成"),
    DELETE(5,"刪除"),
    ;

    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
