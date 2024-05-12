package com.yuu.stock.stockservice.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Stock implements Serializable {

    /**
     * 库存ID
     */
    private Long skuId;

    /**
     * 名称
     */
    private String name;

    /**
     * 库存数量
     */
    private Integer stockNum;
    /**
     * 乐观锁版本号
     */
    private Long version;
    private String createBy;
    private Date createTime;

    private String modifyBy;
    private Date modifyTime;
}
