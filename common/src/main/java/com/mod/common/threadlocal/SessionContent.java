package com.mod.common.threadlocal;

import com.mod.common.core.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 描述当前类
 * @Author Mr.p Email:huangdemo@shein.com
 * @Date create in 2019/7/31 14:02
 */
public class SessionContent{

    private static final Logger logger = LoggerFactory.getLogger(SessionContent.class);

    private final static ThreadLocal<SysUser> USER_LOCAL = new ThreadLocal<>();

    public static void set(SysUser user){
        USER_LOCAL.set(user);
    }

    public static SysUser getUser(){
        return USER_LOCAL.get();
    }

    public static Long getUserId(){
        SysUser sysUser = USER_LOCAL.get();
        return sysUser == null?null:sysUser.getUserId();
    }

    public static String getUserName(){
        SysUser sysUser = USER_LOCAL.get();
        return sysUser == null?null:sysUser.getUserName();
    }

    public static String getOpenId(){
        SysUser sysUser = USER_LOCAL.get();
        return sysUser == null?null:sysUser.getOpenid();
    }

    public static void remove(){
        USER_LOCAL.remove();
    }
}
