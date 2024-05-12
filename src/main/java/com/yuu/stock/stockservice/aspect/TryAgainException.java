package com.yuu.stock.stockservice.aspect;


import com.yuu.stock.stockservice.common.ApiException;
import com.yuu.stock.stockservice.common.ApiResultEnum;

/**
 * 更新重试异常
 */
public class TryAgainException extends ApiException {

    public TryAgainException(ApiResultEnum apiResultEnum) {
        super(apiResultEnum);
    }

}
