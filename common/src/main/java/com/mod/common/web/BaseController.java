package com.mod.common.web;

import com.mod.common.core.Result;

public class BaseController {
    /**
     * success
     * @return
     */
    protected Result success(){
        return Result.success();
    }

    /**
     * success
     * @param obj
     * @return
     */
    protected Result success(Object obj){
        return Result.success(obj);
    }

    /**
     * error
     * @return
     */
    protected Result error(){
        return Result.error();
    }

    /**
     * error
     * @param msg
     * @return
     */
    protected Result error(String msg){
        return Result.error(msg);
    }
}
