package com.advanced.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
* JDK8之前传统的api
* Date() 时间日期
* SimpleDateFormat 时间格式化
* Calendar 日历对象
*
* 常用方法
* Calendar.getInstance() 获取当前日历对象
* get()获取日历中某个信息
* getTime()获取日历中记录的日期对象
* getTimeInMillis()获取时间毫秒值
* set() 修改日历的某个信息
* add() 为某个信息增加/减少指定的值
* */
public class JDk8OldDemo {
    public static void main(String[] args) throws ParseException {
        Date d = new Date();
        System.out.println(d);
        long t = d.getTime();
        System.out.println(t);
        SimpleDateFormat s = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss EEE a");
        String st = s.format(d);
        System.out.println(st);
        System.out.println(s.format(t));

        //字符串时间转换成日期对象
        String dstr = "2023-10-1 10:00:00";
        SimpleDateFormat st1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date sp =  st1.parse(dstr);
        System.out.println(sp);

        // 得到系统此刻时间对应的日历对象
        System.out.println("------------------------");
        Calendar cnow = Calendar.getInstance();
        System.out.println(cnow);
        // get()获取日历中某个信息
        int year = cnow.get(Calendar.YEAR);
        System.out.println(year); // 2023

        int month = cnow.get(Calendar.MONTH);
        System.out.println(month + 1); // 11
        // getTime()获取日历中记录的日期对象
        Date cd = cnow.getTime();
        System.out.println(cd); // Mon Nov 27 19:38:07 CST 2023

        // getTimeInMillis()获取时间毫秒值
        long ct = cnow.getTimeInMillis();
        System.out.println(ct); // 1701085149900

        // set() 修改日历的某个信息
        cnow.set(Calendar.DAY_OF_MONTH,30);
        System.out.println(cnow.getTime()); // Thu Nov 30 19:46:17 CST 2023

        // add() 为某个信息增加/减少指定的值
        cnow.add(Calendar.DAY_OF_MONTH,-3);
        System.out.println(cnow.getTime()); // Mon Nov 27 19:47:12 CST 2023

    }
}
