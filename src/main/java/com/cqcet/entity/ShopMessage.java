package com.cqcet.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 那个谁 on 2018/10/30.
 */
@Data
public class ShopMessage {
    private Integer id;
    //商品标题
    private String title;
    //商品分类id
    private Integer categoryId;
    //用户id
    private Integer userId;
    //商品价格
    private Float price;
    //商品优惠价格
    private Float promotePrice;
    //库存
    private int stock;
    //商品图片地址
    private String imgUrl;
    //商品描述
    private String content;
    //商品上架日期
    private Date updateTime;
    //商品分类名称
    private String categoryName;
    //商品状态
    private int status;

    @Override
    public String toString() {
        return "ShopMessage{" +
                "id=" + id +
                ", userId=" + userId +
                ", stock=" + stock +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", updateTime=" + updateTime +
                ", price=" + price +
                ", promotePrice=" + promotePrice +
                ", imgUrl='" + imgUrl + '\'' +
                ", categoryId=" + categoryId +
                ", status=" + status +
                ", categoryName=" + categoryName +
                '}';
    }
}
