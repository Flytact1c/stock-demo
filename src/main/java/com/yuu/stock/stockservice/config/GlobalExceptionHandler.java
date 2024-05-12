package com.yuu.stock.stockservice.config;

import com.yuu.stock.stockservice.aspect.TryAgainException;
import com.yuu.stock.stockservice.common.ApiException;
import com.yuu.stock.stockservice.common.ApiResultEnum;
import com.yuu.stock.stockservice.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

/**
 * 全局异常捕获
 * @author rstyro
 * @since 2019-03-12
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex){
        logger.error(ex.getMessage(),ex);
        return Result.error(ApiResultEnum.ERROR_MOTHODNOTSUPPORT);
    }

    @ExceptionHandler(TryAgainException.class)
    public Result TryAgainException(TryAgainException ex) {
	    logger.error("全局异常========正在重试");
        return Result.error(ex.getStatus(),ex.getMessage());
    }


    @ExceptionHandler(ApiException.class)
    public Result ApiException(ApiException ex) {
        logger.error(ex.getMessage(),ex);
        return Result.error(ex.getStatus(),ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public Result RuntimeException(RuntimeException ex){
        logger.error(ex.getMessage(),ex);
        return Result.error(ApiResultEnum.ERROR_RUNTION);
    }

    @ExceptionHandler(Exception.class)
    public Result exception(Exception ex){
        logger.error(ex.getMessage(),ex);
        return Result.error(ApiResultEnum.ERROR);
    }

}
