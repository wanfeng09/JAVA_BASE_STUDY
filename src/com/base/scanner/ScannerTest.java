package com.base.scanner;

import java.util.Scanner;

/*
* 步骤：
* 1、导包：告诉程序去JDK的那个包中找扫描器技术
* 2、得到键盘扫描器对象（东西）
* 3、等待接受用户输入数据
* 注意：System、String在JDK中的Java.lang包下
* lang包不需要我们导包，是默认的
* */
public class ScannerTest {
    public static void main(String[] args) {
        Scanner sr = new Scanner(System.in); // 得到键盘扫描器对象（东西）
        System.out.println("请输入你的年龄");
        int age = sr.nextInt(); //开始等待用户输入完成回车，才会继续执行
        System.out.println("你输入的年龄是" + age);
        System.out.println("请输入你的姓名");
        String name = sr.next();
        System.out.println("欢迎回来," + name);
    }
}
