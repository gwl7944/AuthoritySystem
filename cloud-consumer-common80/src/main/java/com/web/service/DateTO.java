package com.web.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ProjectName: cloudTX
 * @Package: com.web.util
 * @ClassName: DateUtil
 * @Author: gwl
 * @Description:  日期时间转换工具
 * @Date: 2020/9/15 15:16
 * @Version: 1.0
 */


 public class DateTO {

    /**
     * 转  年月日 时分秒
     * */

    public static String getStringDateTime(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(date);
        return format;
    }

    public static String getStringDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(date);
        return format;
    }

    public static Date getDateTime(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date format = null;
        try {
            format = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return format;
    }

}
