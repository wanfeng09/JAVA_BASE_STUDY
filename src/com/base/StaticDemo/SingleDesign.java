package com.base.StaticDemo;
/*
* 单例设计模式
* 设计模式就是具体问题的最优解决方案。
* 解决了什么方案？怎么写？
* 确保一个类只有一个对象。【指向同一个内存地址】
*
* 应用场景
* 任务管理器对象，获取运行时对象。【只有一个界面，不会多开】
* 可以避免浪费内存
*
* 饿汉式单例模式
* 拿对象时，对象早就创建好了。
* 写法
* 把类的构造器私有化。
* 定义一个类变量记住类的一个对象。
* 定义一个类方法，返回对象。
*
* 懒汉式单例模式
* 拿对象时，才开始创建对象。
* 写法
* 把类的构造器私有化。
* 定义一个类变量用于存储对象。
* 提供一个类方法，保证返回的是同一个对象。
* */
public class SingleDesign {
    private static SingleDesign sd = new SingleDesign();
    private  SingleDesign(){

    }
    public static SingleDesign get(){
        return sd;
    }
}
