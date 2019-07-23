package com.mod.user.entity.po;

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
@TableName("user_account_flow")
public class AccountFlowPO extends BasePO {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "account_flow_id", type = IdType.AUTO)
    private Long accountFlowId;

    private Long userId;

    /**
     * 流水类型(1 账户金额,2账户积分)
     */
    private Integer flowType;

    /**
     * 变更前
     */
    private Long beforeValue;

    /**
     * 变更值
     */
    private Long changeValue;

    /**
     * 变更后
     */
    private Long afterValue;

    /**
     * 创建人
     */
    private Long createUser;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    private Long updateUser;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 版本
     */
    private Integer version;


}
