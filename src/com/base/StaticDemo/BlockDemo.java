package com.base.StaticDemo;
/*
* 代码块是类的5大成分之一（成员变量、构造器、方法、代码块、内部类）
* 代码块分为两种: 静态代码块 和 实例代码块
*
* 静态代码块
* 格式：static{}
* 特点：类加载时自动执行，由于类只会加载一次，所以静态代码块也只会执行一次
* 作用：完成类的初始化，例如：对类变量的初始化赋值
*
* 实例代码块
* 格式：{}
* 特点：每次创建对象时，执行实例代码块，并在构造器前执行。
* 作用：和构造器一样，都是用来完成对象的初始化的，例如：对实例变量进行初始化赋值。
* */
public class BlockDemo {
    String name;
    static {
        System.out.println("静态代码块执行了");
    }
    public static void test(){
        System.out.println("类方法访问");
    }

    public BlockDemo() {
        System.out.println("无参构造器前执行");
    }

    public BlockDemo(String name) {
        this.name = name;
    }

    {
        System.out.println("实例代码块执行了");
    }
}
