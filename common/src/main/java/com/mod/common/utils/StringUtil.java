package com.mod.common.utils;

import java.io.Serializable;

public class StringUtil implements Serializable {
    /**
     * 移除null
     *
     * @param obj
     * @return
     */
    public static String cutNull(Object obj) {
        if (null == obj) {
            return "";
        }
        String str = obj.toString();

        return "null".equals(str) ? "" : str;
    }

    public static boolean isEmpty(Object obj) {
        return obj == null
                || "".equals(obj.toString().trim());
    }


    /**
     * 空值给默认
     *
     * @param obj
     * @param str
     * @return
     */
    public static String getDefaultStr(Object obj, String str) {
        if (null == obj) {
            return str;
        }
        if ("".equals(obj.toString().trim())) {
            return str;
        }
        return obj.toString();
    }

    public static boolean hasLength(String obj) {
        return !isEmpty(obj);
    }

}
