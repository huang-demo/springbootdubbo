package com.mod.user.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mod.common.entity.po.BasePO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author Mr.p
 * @since 2019-07-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("user_login_log")
public class LoginLogPO extends BasePO {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "login_log_id", type = IdType.AUTO)
    private Long loginLogId;

    /**
     * 用户
     */
    private Long userId;

    private String userName;

    /**
     * 类型 1登录 2退出
     */
    private Integer type;
    /**
     * 状态 1登录成功 2 密码错误
     */
    private Integer state;


    //
    @Getter
    public enum LoginType {
        //
        LOGIN(1,"登录"),
        LOGOUT(2,"退出"),
        ;
        private Integer code;
        private String name;

        LoginType(Integer code, String name) {
            this.code = code;
            this.name = name;
        }
    }
    //
    @Getter
    public enum LoginState{
        //
        SUCCESS(1),
        ERR(2),
        ;
        private Integer code;

        LoginState(Integer code) {
            this.code = code;
        }
    }


}
