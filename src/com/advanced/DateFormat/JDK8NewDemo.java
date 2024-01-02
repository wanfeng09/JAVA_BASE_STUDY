package com.advanced.DateFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/*
* 传统的api Date、SimpleDateFormat、Calendar 【不推荐】
* 都是可变对象，修改后会丢失开始的时间日期
* 线程不安全
* 只能精确到毫秒
*
* JDK8新增的api 【推荐】
* 代替Calendar
* LocalDate: 年、月、日
* LocalTime: 时、分、秒
* LocalDateTime: 年、月、日、时、分、秒
* ZoneId: 时区
* ZonedDateTime: 带时区的时间
*
* 代替Date ---> Instant：时间戳、时间线
*
* 代替SimpleDateFormat ---> DateTimeFormatter: 用于时间的格式化和解析
*
* Duration：时间间隔（时、分、秒、纳秒）
* Period: 时间间隔（年、月、日）
*
* 都是不可变对象，修改后会返回新的时间对象，不会丢失最开始的时间
* 线程安全
* 能精确到毫秒，纳秒
*
* */
public class JDK8NewDemo {
    public static void main(String[] args) {
        // 获取当前时间【不可变】
        LocalDate ld = LocalDate.now();
        System.out.println(ld); // 2023-11-30

        // 年月日
        System.out.println(ld.getYear()); // 2023
        System.out.println(ld.getMonthValue()); // 11
        System.out.println(ld.getDayOfMonth()); // 30
        // 一年中的第几天
        System.out.println(ld.getDayOfYear()); // 334
        // 星期几
        System.out.println(ld.getDayOfWeek()); // THURSDAY
        System.out.println(ld.getDayOfWeek().getValue()); // 4

        // 查找修改某个信息
        // 修改年月日
        LocalDate ld2 = ld.withYear(2026);
        System.out.println(ld2); // 2026-11-30
        System.out.println(ld.withMonth(1)); // 2023-01-30
        System.out.println(ld.withDayOfMonth(26)); // 2023-11-26
        System.out.println(ld); // 2023-11-30 当天时间

        System.out.println("--------------------");
        // 把某个信息加多少
        // 加3年
        LocalDate lda = ld.plusYears(3); // 2026-11-30
        System.out.println(lda);
        // 加1个月
        System.out.println(ld.plusMonths(1)); // 2023-12-30
        // 加3天
        System.out.println(ld.plusDays(3));// 2023-12-03
        // 加1个星期
        System.out.println(ld.plusWeeks(1)); // 2023-12-07

        // 把某个信息减多少
        System.out.println(ld.minusYears(2)); // 2021-11-30
        System.out.println(ld.minusMonths(2)); // 2023-09-30
        System.out.println(ld.minusDays(2)); // 2023-11-28
        System.out.println(ld.minusWeeks(2)); // 2023-11-16

        System.out.println("=======================");
        // 获取指定日期对象
        LocalDate ld6 = LocalDate.of(2028,8,8);
        LocalDate ld7 = LocalDate.of(2028,8,8);
        // 判断两者是否相等
        System.out.println(ld6.equals(ld7)); // true
        // 判断日期在后
        System.out.println(ld6.isAfter(ld7)); // false
        // 判断日期在前
        System.out.println(ld6.isBefore(ld7)); // false

        // 用法与LocalDate一致
        LocalTime lt = LocalTime.now();
        System.out.println(lt); // 19:23:18.720
        // 获取纳秒
        System.out.println(lt.getNano()); // 720000000

        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt); // 2023-11-30T19:23:18.720

        // 可以把LocalDateTime转换成LocalDate或者LocalTime
        LocalDate ldd = ldt.toLocalDate();
        System.out.println(ldd); // 2023-11-30

        LocalTime ltt = ldt.toLocalTime();
        System.out.println(ltt); // 19:23:18.720
    }
}
