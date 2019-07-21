package com.mod.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class GsonUtils{
    /**
     * Gson对象
     */
    public static Gson gson;

    static{
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }

    public static String obj2Json(Object obj){
        return gson.toJson(obj);
    }

    public static <T> T  json2Obj(String json,Class<T> cls){
        if(json == null||"".equals(json.trim())){
            return null;
        }
        return gson.fromJson(json,cls);
    }

    public static <T> List<T> json2List(String json,Class<T> cls){
        List<T> list = null;
        if(gson != null){
            list = gson.fromJson(json,new TypeToken<List<T>>(){
            }.getType());
        }
        return list;
    }
}
