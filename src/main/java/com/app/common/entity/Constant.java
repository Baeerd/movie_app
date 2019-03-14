package com.app.common.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 公共常量
 */
public class Constant {

    public static final String USER_ORDER_LIST = "userOrderList";

    public static Map<String, String[]> SHOW_NO_MAP = new HashMap<String, String[]>(){
        private static final long serialVersionUID = 1L;
        {
            put("1", new String[]{"08:00", "10:00"});
            put("2", new String[]{"10:00", "12:00"});
            put("3", new String[]{"13:00", "15:00"});
            put("4", new String[]{"15:00", "17:00"});
            put("5", new String[]{"17:00", "19:00"});
            put("6", new String[]{"19:00", "21:00"});
            put("7", new String[]{"21:00", "23:00"});
        }
    };
}
