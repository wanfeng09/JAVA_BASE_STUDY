package com.advanced.BigDecimalDemo;
/*
 * BigDecimal用于解决浮点型运算时，出现结果失真的问题
 * 常用方法
 * valueOf() 转换double成BigDecimal
 * add() 加
 * subtract() 减
 * multiply() 乘
 * divide() 除
 * doubleValue() 将BigDecimal转换成double
 * */
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Demo {
    public static void main(String[] args) {
        // BigDecimal解决小数运算问题
        double a = 0.1;
        double b = 0.2;
        double c= a + b;
        System.out.println(c); // 0.30000000000000004 失精
        BigDecimal a1 = BigDecimal.valueOf(a);
        BigDecimal b1 = BigDecimal.valueOf(b);
        // 加法
        BigDecimal c1 = a1.add(b1);
        System.out.println(c1); // 0.3

        // 减法
        BigDecimal c2 = a1.subtract(b1);
        System.out.println(c2); // -0.1

        // 乘法
        BigDecimal c3 = a1.multiply(b1);
        System.out.println(c3); // 0.02

        // 除法
        BigDecimal c5 = a1.divide(b1);
        System.out.println(c5); // 0.5

        // 特殊 0.1/0.3 = 0.3333333除不尽
        BigDecimal i = BigDecimal.valueOf(0.1);
        BigDecimal j = BigDecimal.valueOf(0.3);
        // BigDecimal c51 = i.divide(j); // 报错--没有可精确表示的十进制结果。
        // 解决
        BigDecimal c51 = i.divide(j, 2, RoundingMode.HALF_UP);
        System.out.println(c51); // 0.33

        // 把BigDecimal转换成double数据类型
        double d = c51.doubleValue();
        System.out.println(d);



    }
}
