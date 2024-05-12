package com.yuu.stock.stockservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDTO {
    /**
     * 商品id
     */
    private Long stockId;
    /**
     * 下单数量
     */
    private Integer num;

}
