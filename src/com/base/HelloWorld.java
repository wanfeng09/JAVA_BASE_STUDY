package com.base;
/*
* 注意：byte short char在表达式中是直接提升成int运算的
* 两个整数相除一定是整数
* */
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("hello world");
        /*System.out.println('A');
        // 定义float类型变量，默认double类型，为了防止类型不兼容，需在结尾加F
        float a = 13.14F;
        System.out.println(a);
        // 定义long类型变量，默认int类型，为了防止整数过大，需在结尾加F
        long b = 10000000000000L;
        System.out.println(b);
        int a = 10;
        System.out.println(a);
        short b = (short)a;
        System.out.println(b);
        byte c1 = 12;
        System.out.println(c1);
        int d = c1 * 12;
        System.out.println(d);*/


        byte a1 = 10;
        byte b2 = 12;
//        byte c3 = a1 + b2; // 报错
//        int c3 = a1 + b2; // 22
        byte c3 = (byte)(a1 + b2);
        System.out.println(c3);

        System.out.println(10 / 3); // 3

    }
}
