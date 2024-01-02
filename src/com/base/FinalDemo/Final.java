package com.base.FinalDemo;
/*
* final关键字，可以修饰（类、方法，变量）
* 修饰类：最终类，特点是不能被继承了。
* 修饰方法：最终方法，特点是不能重写了。
* 修饰变量：该变量只能被赋值一次。
*
* 注意
* final修饰基本类型的变量，数据不能改变。
* final修饰引用类型的变量，变量的存储地址不能被改变，但内容可以改变。
*
* 常量【大写，多个单词下划线连接】
* 使用了static final修饰的成员变量就被称为常量
* 代码可读性好，可维护性也更好
* 程序编译后，常量会被“宏替换”；出现常量的地方全部会被替换成其记住的字面量，这样就可以保证使用常量和直接使用字面量的性能是一样的。
* */
public class Final {
    /*
    * 变量分： 成员变量跟局部变量
    * 成员变量分：静态变量（类变量）跟实例变量
    * */
    // 局部变量
    final int a  = 10;
    // 常量
    static final double b = 3.14;
    public static final void run(){
        System.out.println("最终类");
    }
}

class A extends Final{
//    public static void  run(){
//        System.out.println("最终类不能重写");
//    }
}
