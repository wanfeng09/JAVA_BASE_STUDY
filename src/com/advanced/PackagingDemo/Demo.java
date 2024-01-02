package com.advanced.PackagingDemo;

import java.util.ArrayList;

/*
* 包装类：基本类型的数据包装成对象【万物皆对象】
* 基本数据类型：byte,short,int,long,char,float,double,boolean
* 包装类(引用数据类型)：Byte,Short,Integer,Long,Character,Float,Double,Boolean
* 自动装箱：基本数据类型可以自动转换为包装类型
* 自动拆箱：包装类型可以自动转换基本数据类型
* */
public class Demo {
    public static void main(String[] args) {
        // 基本类型装换字符串
        Integer a = 23;
        String sa = Integer.toString(a);
        System.out.println(sa + 1); // 231

        String sa2 = a.toString();
        System.out.println(sa2 + 1);// 231

        String sa3 = a + "";
        System.out.println(sa3 + 1); // 231

        // 字符串类型转换对应的基本类型
        String  s = "29";
        int is1 = Integer.valueOf(s);
        System.out.println(is1 +1); // 30
        String ds = "99.5";
        double ds1 = Double.valueOf(ds);
        System.out.println(ds1+0.56);// 100.06

        System.out.println("-----------包装类基本使用-----------");
        Integer i1 = new Integer(12); // 已过时
        System.out.println(i1 + 1); // 13
        Integer i2 = Integer.valueOf(26); // 建议使用
        System.out.println(i2 + 2); // 28

        // 自动装箱
        Integer i3 = 12;
        // 自动拆箱
        int i6 = i3;
        System.out.println(i3);
        System.out.println(i6);
        System.out.println(i3 == i6); // true

        // 泛型和集合不支持基本数据类型，只能支持引用数据类型
        ArrayList<Integer> alist = new ArrayList<>();
        alist.add(12);// 自动装箱
        alist.add(16);// 自动装箱
//        alist.add("dsds");

        int i7 = alist.get(0);
        System.out.println(i7);// 自动拆箱
    }
}
