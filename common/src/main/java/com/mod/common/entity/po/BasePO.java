package com.mod.common.entity.po;


import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

@Data
public class BasePO implements Serializable{
    private Long createUser;
    private Date createTime;
    private Long updateUser;
    private Date updateTime;
    private Long version;

    @Getter
    public enum State{
        //
        ENABLE(1,"启用"),
        DISABLE(2,"禁用"),
        ;
        private Integer code;
        private String name;

        State(Integer code,String name){
            this.code = code;
            this.name = name;
        }
    }
    public enum Deleted{
        //
        YES(1,"已删除"),
        NO(0,"未删除"),
        ;
        private Integer code;
        private String name;

        Deleted(Integer code,String name){
            this.code = code;
            this.name = name;
        }
    }
}
