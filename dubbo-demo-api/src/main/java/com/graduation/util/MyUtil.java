package com.graduation.util;

import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyUtil {

    public static boolean isEmpty(String str) {
        if (str == null) {
            return true;
        } else if (str.length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isPhone(String str) {
        String reg = "^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$";// 手机号正则
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static boolean isNotPhone(String str) {
        return !isPhone(str);
    }

    public static boolean isNum(String str) {
        String reg = "^[0-9]*[1-9][0-9]*$";// 数字正则
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static String changeSub(String type, String msg) {
        if ("名字".equals(type)) {
            return " user_name = '" + msg + "'";
        } else if ("性别".equals(type)) {
            return " sex = '" + msg + "'";
        } else if ("省".equals(type)) {
            return " province = '" + msg + "'";
        } else if ("市".equals(type)) {
            return " city = '" + msg + "'";
        } else if ("个性签名".equals(type)) {
            return " signature = '" + msg + "'";
        }
        return " user_name = '" + msg + "'";
    }

    public static Object formatTime(Object date) {
        SimpleDateFormat sj = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        return sj.format(date);
    }

    public static String getToFrom(Map<String, Object> chatRoom) {
        return chatRoom.get("toId").toString() + "," + chatRoom.get("fromId").toString();
    }

    public static String getFromTo(Map<String, Object> chatRoom) {
        return chatRoom.get("fromId").toString() + "," + chatRoom.get("toId").toString();
    }
}
