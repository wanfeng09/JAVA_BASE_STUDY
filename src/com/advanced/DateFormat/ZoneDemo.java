package com.advanced.DateFormat;

import java.time.*;
import java.time.format.DateTimeFormatter;

/*
* 时区Id ZoneId
* 时区日期时间 ZonedDateTime
* 时间线上的某个时刻/时间戳 Instant
* 一段时间 Period 计算两个LocalDate相差的年数、月数、天数
* 持续时间 Duration 计算两个时间对象相差的天数、小时数、分数、秒数、纳秒数，支持LocalTime/LocalDateTime
*
* */
public class ZoneDemo {
    public static void main(String[] args) {

        // 获取系统默认时区
        ZoneId z = ZoneId.systemDefault();
        System.out.println(z); // Asia/Shanghai
        System.out.println(z.getId()); // Asia/Shanghai
        // 获取java支持的全部时区
        System.out.println(ZoneId.getAvailableZoneIds());
        ZoneId z1 = ZoneId.of("America/New_York");
        System.out.println(z1); // America/New_York
        // 获取某个时区的标准时间【中国-12】
        ZonedDateTime nn = ZonedDateTime.now(z1);
        System.out.println(nn); // 2023-11-30T06:38:42.552-05:00[America/New_York]
        // 世界标准时间 【中国-8】
        ZonedDateTime nn1 = ZonedDateTime.now(Clock.systemUTC());
        System.out.println(nn1); // 2023-11-30T11:38:42.560Z
        // 系统默认时间
        ZonedDateTime mn = ZonedDateTime.now();
        System.out.println(mn); // 2023-11-30T19:38:42.560+08:00[Asia/Shanghai]

        // 获取年月日时分秒纳秒等
        System.out.println(mn.getYear()); // 2023
        System.out.println(mn.getMonth().getValue()); // 11
        System.out.println(mn.getDayOfMonth()); // 30
        System.out.println(mn.getDayOfWeek().getValue());
        System.out.println(mn.getHour());
        System.out.println(mn.getMinute());
        System.out.println(mn.getSecond());
        System.out.println(mn.getNano());

        //  修改时间 with... 减少时间 minus...  增加时间plus...

        System.out.println("--------------------");

        // 不可变对象
        // 当前系统时间【中国】
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt); // 2023-11-30T19:59:01.109

        // 时间标准时间
        Instant now = Instant.now();

        // 获取总秒数
        System.out.println(now.getEpochSecond()); // 1701345541

        // 不够1秒的纳秒数
        System.out.println(now.getNano()); // 109000000
        // 加多少纳秒
        Instant now2 = now.plusNanos(111);
        System.out.println(now); //2023-11-30T11:59:01.109Z
        System.out.println(now2); // 2023-11-30T11:59:01.109000111Z

        System.out.println("------------------");
        LocalDate ll1 = LocalDate.of(2023,11,12);
        LocalDate ll2 = LocalDate.of(2023,11,12);
        Period p1 = Period.between(ll1,ll2);
        System.out.println(p1.getYears()); // 0
        System.out.println(p1.getMonths()); // 0
        System.out.println(p1.getDays()); // 0

        System.out.println("---------------");

        LocalDateTime ltd6 = LocalDateTime.of(2023,11,12,11,10,30);
        LocalDateTime ltd7 = LocalDateTime.of(2023,11,12,11,12,36);
        Duration d = Duration.between(ltd6,ltd7);
        System.out.println(d.toHours()); // 0
        System.out.println(d.toMillis()); // 126000
        System.out.println(d.toMinutes()); // 2

        System.out.println("------------");
        // 创建一个日期格式化器对象出来
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");

        // 进行格式化
        LocalDateTime ltn = LocalDateTime.now();
        System.out.println(ltn);

        String ltns = dt.format(ltn); // 正向格式化
        System.out.println(ltns);

        String ltns2 = ltn.format(dt); // 反向格式化
        System.out.println(ltns2);

        // 解析时间
        String str = "2023年11月30日 20:18:06";
        LocalDateTime dstr = LocalDateTime.parse(str,dt);
        System.out.println(dstr); // 2023-11-30T20:18:06


    }
}
