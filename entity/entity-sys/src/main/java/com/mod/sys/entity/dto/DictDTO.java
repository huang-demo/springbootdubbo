package com.mod.sys.entity.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class DictDTO implements Serializable {
    private Long dictId;
    private Long pid;
    private String code;
    private String name;
    private String value;
}
