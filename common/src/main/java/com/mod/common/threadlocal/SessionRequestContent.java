package com.mod.common.threadlocal;

import com.mod.common.core.GlobalRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SessionRequestContent {

    private static final Logger logger = LoggerFactory.getLogger(SessionRequestContent.class);

    private final static ThreadLocal<GlobalRequest> REQUEST_LOCAL = new ThreadLocal<>();

    public static void setRequest(GlobalRequest token) {
        REQUEST_LOCAL.set(token);
    }
    public static GlobalRequest getRequest() {
        return REQUEST_LOCAL.get();
    }

    public static void remove() {
        REQUEST_LOCAL.remove();
    }
    public static void print(){
        GlobalRequest request = getRequest();
        if (request == null){
            return;
        }
        logger.info("USER: {},IP: {},URI: {},TIMES: {} ms",request.getUserName(),request.getIp(),request.getUri(),System.currentTimeMillis()-request.getStart());
    }

}
