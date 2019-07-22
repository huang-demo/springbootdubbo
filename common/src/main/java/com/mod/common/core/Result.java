package com.mod.common.core;


/**
 * @author Mr.p
 */
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static Result success(Object obj) {
        Result res = new Result();
        res.setCode(200);
        res.setMsg("操作成功!");
        res.setData(obj);
        return res;
    }

    public static Result success() {
        Result res = new Result();
        res.setCode(200);
        res.setMsg("操作成功!");
        return res;
    }

    public static Result error() {
        Result res = new Result();
        res.setCode(500);
        res.setMsg("操作异常!");
        return res;
    }

    public static Result error(String msg) {
        Result res = new Result();
        res.setCode(500);
        res.setMsg(msg);
        return res;
    }
}