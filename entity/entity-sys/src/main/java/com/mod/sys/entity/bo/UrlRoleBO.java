package com.mod.sys.entity.bo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UrlRoleBO implements Serializable {
    private String url;
    private String roleCode;
}
