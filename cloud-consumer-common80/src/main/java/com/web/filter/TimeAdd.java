package com.web.filter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @ProjectName: cloudTX
 * @Package: com.web.filter
 * @ClassName: TimeAdd
 * @Author: ywj
 * @Description:
 * @Date: 2020/9/15 17:38
 */
public class TimeAdd {

    /**
     * 给时间加上几个小时
     * @param day 当前时间 格式：yyyy-MM-dd HH:mm:ss
     * @param hour 需要加的时间
     * @return
     */
    public static String addDateMinut(String day, int hour){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(day);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (date == null)
            return "";
        System.out.println("front:" + format.format(date)); //显示输入的日期
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, hour);// 24小时制
        date = cal.getTime();
        System.out.println("after:" + format.format(date));  //显示更新后的日期
        cal = null;
        return format.format(date);

    }
    /*
    * 将时间增加多少秒
     */
    public static String addDateS(Date day,int s) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date =df.parse(df.format(day));
        date.setTime(date.getTime() + s*1000);
        System.out.println("当前时间："+df.format(date));
        return df.format(date);
    }


    /*public static void main(String[] args) throws ParseException {
        addDateS("2020-09-16 09:14:00",12600);
    }*/
}
