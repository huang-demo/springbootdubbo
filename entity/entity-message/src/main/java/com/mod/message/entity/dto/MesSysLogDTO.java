package com.mod.message.entity.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Description: 描述当前类
 * @Author Mr.p Email:huangdemo@shein.com
 * @Date create in 2019/9/5 11:05
 */
@Data
public class MesSysLogDTO{
    private String level;
    private String className;
    private String message;
    private String module;
    private Date requestTime;
    private String traceId;
}
