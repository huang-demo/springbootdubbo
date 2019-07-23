package com.mod.sys.entity.po;

import com.mod.common.entity.po.BasePO;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author Mr.p
 * @since 2019-07-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_dict")
public class DictPO extends BasePO {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "dict_id", type = IdType.AUTO)
    private Long dictId;

    /**
     * code
     */
    private String code;

    /**
     * 名称
     */
    private String dictName;

    /**
     * 值
     */
    private String dictValue;

    /**
     * 类型
     */
    private Integer dictType;

    /**
     * 状态
     */
    private Integer dictState;

    /**
     * 父级
     */
    private Long parentId;



}
