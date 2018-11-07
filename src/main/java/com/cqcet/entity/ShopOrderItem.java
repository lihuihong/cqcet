package com.cqcet.entity;

import lombok.Data;

/**
 * Created by 那个谁 on 2018/11/6.
 */
@Data
public class ShopOrderItem {
    private Integer id;

    private Integer pid;

    private Integer oid;

    private Integer uid;

    private Integer number;

    private ShopMessage shopMessage;

}
