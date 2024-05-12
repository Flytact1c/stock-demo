package com.yuu.stock.stockservice.mapper;

import com.yuu.stock.stockservice.entity.Stock;
import org.springframework.stereotype.Component;

@Component
public interface StockMapper {
    public boolean updateStockNum(Stock stock);
    public Stock getStock(Long skuId);
}
