package com.mod.common.constant;

import lombok.Getter;

/**
 * @Description: 描述当前类
 * @Author Mr.p Email:
 * @Date create in 2019/7/18 14:19
 */
@Getter
public enum ExceptionCode{
    DATA_NOT_EXIST(1,"数据不存在"),
    USER_NOT_EXIST(1,"用户不存在"),
    VALIDATE_PARAM(2,"参数校验不通过"),
    NO_LOGIN(3,"用户未登录"),
    WRONG_PASSWORD(4,"密码错误"),
    ;
    private Integer code;
    private String name;

    ExceptionCode(Integer code,String name){
        this.code = code;
        this.name = name;
    }}
