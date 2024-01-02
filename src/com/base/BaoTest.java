package com.base;

import com.base.BaoDemo1.BaoDemo;

/*
 * 什么是包？
 * 包用来分门别类的管理各种不同程序的，类似文件夹，建包有利于程序的管理和维护
 * 导包 import 包名.类名
 * 注意
 * 同一个包下的类，互相可以直接调用
 * java.lang包下不需要导包，默认可以直接使用
 * 要调用多个不同包下的程序，程序名一样，只能导入一个包，另一个程序必须带包名访问
 * */
public class BaoTest {
    public static void main(String[] args) {
        BaoDemo d1 = new BaoDemo();
        d1.testDemo();
        com.base.BaoDemo2.BaoDemo d2 = new com.base.BaoDemo2.BaoDemo();
        d2.testDemo();
    }
}
