package com.mod.common.exception;


import com.mod.common.constant.ExceptionCode;

/**
 * @Description: 描述当前类
 * @Author Mr.p Email:huangdemo@shein.com
 * @Date create in 2019/7/19 10:48
 */
public class GlobalException extends RuntimeException{

    public GlobalException(){

    }

    public GlobalException(String msg){
        super(msg);
    }

    public GlobalException(ExceptionCode code){
        super(code.getName());

    }
}
