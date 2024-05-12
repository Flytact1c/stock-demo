package com.yuu.stock.stockservice.service.impl;

import com.yuu.stock.stockservice.aspect.IsTryAgain;
import com.yuu.stock.stockservice.aspect.TryAgainException;
import com.yuu.stock.stockservice.common.ApiException;
import com.yuu.stock.stockservice.common.ApiResultEnum;
import com.yuu.stock.stockservice.common.Result;
import com.yuu.stock.stockservice.dto.OrderDTO;
import com.yuu.stock.stockservice.entity.Stock;
import com.yuu.stock.stockservice.mapper.StockMapper;
import com.yuu.stock.stockservice.service.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Service
@Transactional
public class StockService implements IStockService {

    @Autowired
    private StockMapper stockMapper;

    /**
     * 下单
     * @param dto
     * @return
     * @throws Exception
     */
    @Transactional
    @IsTryAgain
    public Result order(OrderDTO dto) throws Exception {
        Integer num = dto.getNum();
        Stock stock = stockMapper.getStock(dto.getStockId());
        if(stock == null){
            throw new ApiException(ApiResultEnum.ERROR);
        }
        int finalNum = stock.getStockNum() - num;
        if(finalNum<0){
            throw new ApiException(ApiResultEnum.ERROR);
        }
        stock.setStockNum(finalNum);
        stock.setModifyBy("sys");
        stock.setModifyTime(new Date());
        if(!stockMapper.updateStockNum(stock)){
            //如果更新失败就抛出去，重试
            throw new TryAgainException(ApiResultEnum.ERROR_TRY_AGAIN);
        }
        return Result.ok(dto.getNum());
    }



    /**
     * 更新账户余额
     * @param userId 账户用户ID
     * @param amount    操作金额数量
     * @param state     转入--1 ，转出 -- 0
     * @throws Exception
     */
    public void updateAccount( Long userId, BigDecimal amount,int state) throws Exception{

    }
}
