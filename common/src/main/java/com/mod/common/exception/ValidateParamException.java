package com.mod.common.exception;

import com.mod.common.constant.ExceptionCode;
import lombok.Data;

/**
 * @Description: 描述当前类
 * @Author Mr.p Email:
 * @Date create in 2019/7/18 14:15
 */
@Data
public class ValidateParamException extends RuntimeException{

    private Integer code;
    private String msg;

    public ValidateParamException(){
    }

    public ValidateParamException(String message){
        super(message);
    }
    public ValidateParamException(ExceptionCode exceptionCode){
        super(exceptionCode.getName());
        this.code = exceptionCode.getCode();
        this.msg = exceptionCode.getName();
    }
}
