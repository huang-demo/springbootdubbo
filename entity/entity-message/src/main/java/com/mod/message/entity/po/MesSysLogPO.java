package com.mod.message.entity.po;

import com.mod.common.entity.po.BasePO;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

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
 * @since 2019-09-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mes_sys_log")
public class MesSysLogPO extends BasePO{

    private static final long serialVersionUID = 1L;

    @TableId(value = "sys_log_id", type = IdType.AUTO)
    private Long sysLogId;

    /**
     * 模块名
     */
    private String module;

    /**
     * 类型1 info 2 warn 3 err
     */
    private Integer level;

    /**
     * 内容
     */
    private String message;
    /**
     *
     */
    private String traceId;

    /**
     * 类
     */
    private String className;

    /**
     * 报错时间
     */
    private Date requestTime;


    @Getter
    public enum Level{

        //错误级别
        INFO(1,"INFO"),
        WARN(2,"WARN"),
        ERROR(3,"ERROR"),
        ;
        private Integer code;
        private String name;

        Level(Integer code,String name){
            this.code = code;
            this.name = name;
        }

        private static Map<String,Level> key_map;
        static {
            key_map = new LinkedHashMap<>();
            Level[] levels = Level.values();
            for(Level level: levels){
                key_map.put(level.getName(),level);
            }
        }


        public static Level getLevel(String name){
            return key_map.get(name);
        }
    }


}
