package com.yuu.stock.stockservice.controller;

import com.yuu.stock.stockservice.common.Result;
import com.yuu.stock.stockservice.dto.OrderDTO;
import com.yuu.stock.stockservice.service.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private IStockService stockService;

    @PostMapping("/order")
    public Result transfter(OrderDTO dto) throws Exception {
        return stockService.order(dto);
    }
}
