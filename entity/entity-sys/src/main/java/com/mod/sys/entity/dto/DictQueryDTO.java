package com.mod.sys.entity.dto;

import com.mod.common.core.BasePage;
import lombok.Data;

@Data
public class DictQueryDTO extends BasePage {

    private Integer pid;

    private String dictName;

}
