package com.mod.message.entity.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 描述当前类
 * @Author Mr.p Email:
 * @Date create in 2019/9/5 11:05
 */
@Data
public class MesSysLogDTO implements Serializable{
    private String level;
    private String className;
    private String message;
    private String module;
    private Date requestTime;
    private String traceId;
}
