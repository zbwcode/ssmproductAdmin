package com.itheima.ssm.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    //日期转字符串
    public static String DateToString(Date date,String patt){
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        String dateString = sdf.format(date);
        return  dateString;
    }

    //字符串转日期
    public static Date StringToDate(String dateString,String patt) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        Date date = sdf.parse(dateString);
        return date;
    }
}
