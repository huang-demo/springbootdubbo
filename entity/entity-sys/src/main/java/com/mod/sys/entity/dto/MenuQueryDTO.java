package com.mod.sys.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 描述当前类
 * @Author Mr.p Email:
 * @Date create in 2019/7/24 15:50
 */
@Data
public class MenuQueryDTO implements Serializable{

    private Long menuId;
    private Long pid;
    private Integer level;
    private Integer withChildern;
}
