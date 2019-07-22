package com.mod.common.core;

import lombok.Data;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * @Description: 描述当前类
 * @Author Mr.p Email:huangdemo@shein.com
 * @Date create in 2019/7/18 15:00
 */
@Data
public class BasePage implements Serializable{

    @Min(value = 0,message = "请输入正确的页码")
    private Integer pageIndex;
    @Min(value = 1,message = "页面范围[1,100]")
    private Integer pageSize;
}
