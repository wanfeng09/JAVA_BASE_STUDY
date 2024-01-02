package com.base.EnumDemo;

public class Test {
    public static void main(String[] args) {
        // 访问枚举
        Demo a = Demo.A;
        System.out.println(a); // A
        // 枚举的一些api
        Demo[] as = Demo.values(); // 获取所有枚举对象
        System.out.println(as[1]);// B

        Demo a1 = Demo.valueOf("A"); // 拿到枚举对象
        System.out.println(a1); // A
        System.out.println(a1.name()); // A
        System.out.println(a1.ordinal());// 索引

        // 抽象枚举
        ABDemo ab = ABDemo.B;
        ab.test();
        check(List.BOY);
    }

    public static void check(List sex){
        switch (sex){
            case BOY:
                System.out.println("boy");
                break;
            case GIRL:
                System.out.println("girl");
                break;
        }
    }
}
