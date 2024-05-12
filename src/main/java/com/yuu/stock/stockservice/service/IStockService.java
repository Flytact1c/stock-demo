package com.yuu.stock.stockservice.service;


import com.yuu.stock.stockservice.common.Result;
import com.yuu.stock.stockservice.dto.OrderDTO;

public interface IStockService {
    /**
     * 下单
     * @param dto
     * @return
     * @throws Exception
     */
    public Result order(OrderDTO dto) throws Exception;

}
