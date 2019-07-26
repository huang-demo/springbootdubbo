package com.mod.user.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mod.common.entity.po.BasePO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author Mr.p
 * @since 2019-07-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("user_info")
public class UserInfoPO extends BasePO{

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    private String userName;

    private String password;

    private String openId;

    private String avator;

    private String appid;

    private String phone;

    private String email;

    private Integer userState;

    private Integer userType;

    @Getter
    public enum UserType{
        //用户类型
        FANS(1,"粉丝"),
        AGENT(2,"代理商"),
        SYSTEM_USER(8,"系统用户"),
        SUPPER_ADMIN(9,"超级管理"),
        ;

        private Integer code;
        private String name;

        UserType(Integer code,String name){
            this.code = code;
            this.name = name;
        }

        private static Map<Integer,UserType> KEY_MAP;

        static {
            KEY_MAP = new LinkedHashMap<>(5);
            for(UserType ty: UserType.values()){
                KEY_MAP.put(ty.getCode(),ty);
            }
        }
        public static UserType getUserType(Integer code){
            return KEY_MAP.get(code);
        }
    }


}
