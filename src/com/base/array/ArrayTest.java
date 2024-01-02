package com.base.array;

import java.util.Random;

/*
* 数组：引用数据类型，存储的数组在内存中的地址信息
* 静态
* 数据类型[] 数据名 = new 数据类型[]{元素1，元素2,元素3} 推荐
* 数据类型 数据名[] = new 数据类型[]{元素1，元素2,元素3}
* 简化：数据类型[] 数据名 = {元素1，元素2,元素3}
* 动态
* 数据类型[] 数据名 = new 数据类型[数组长度]
* 基本数据类型：byte/short/char/int/long 默认值0
* float/double 默认值0.0
* boolean 默认值false
* 引用数据类型
* 类、接口、数组、String 默认值 null
* java内存分配
* 方法区：字节码文件.class main方法
* 栈内存：变量,运行的方法，main方法
* 堆内存： new出来的对象会在这块内存中开辟空间并产生地址
*
* 注意： 如果某个数组变量中存储的null,代表这个数组变量没有指向任何数组对象了【堆内存中内存地址】，可以输出这个变量null
* 但是不能用这个变量访问数组数据arr[0]跟数组长度arr.length，会报空指针异常错误 NullPointerException
* */
public class ArrayTest {
    public static void main(String[] args) {
//        int[] num = {1,2,3,4};
//        System.out.println(num); // 看不到信息，num是变量，在栈内存中，num中存储的是数组对象在堆内存中的地址值
//        for (int j : num) {
//            System.out.println(j);
//        }
//        char[] num3 = new char[3];
//        System.out.println((int)num3[0]); // 0
//        System.out.println(num3[0]); // 乱码
//        String[] num2 = new String[3];
//        System.out.println(num2[0]); // null

        // 随机数数组
        int[] random1 = new int[5];
        Random rd = new Random();
        for (int i = 0; i < random1.length; i++) {
            random1[i] = rd.nextInt(10); // 0-9随机数
        }

        for (int i = 0; i < random1.length; i++) {
            System.out.println("随机数数组---" + random1[i]);
        }

        // 数组反转
        int[] arr = {10,20,30,50,60};
        for (int i = 0, j = arr.length - 1; i < j; i++,j--) {
            int temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.println("数组反转---" + arr[i]);
        }
    }
}
