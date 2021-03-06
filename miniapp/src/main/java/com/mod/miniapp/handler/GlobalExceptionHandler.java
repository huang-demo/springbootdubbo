package com.mod.miniapp.handler;

import com.mod.common.core.Result;
import com.mod.common.exception.GlobalException;
import com.mod.common.exception.ValidateParamException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description: 描述当前类
 * @Author Mr.p Email:
 * @Date create in 2019/7/18 14:31
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(ValidateParamException.class)
    public Result handleValidateParamException(ValidateParamException e) {
        log.warn("参数异常拦截:{}",e.getMessage());
        return Result.error(e.getMessage());
    }
    @ExceptionHandler(GlobalException.class)
    public Result handleGlobalException(GlobalException e) {
        log.warn("参数异常拦截:{}",e.getMessage());
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error("全局错误拦截:{}",e.getMessage());
        return Result.error("服务器繁忙!");
    }
}
