package com.base.StaticDemo;
/*
* 懒汉式单例模式
* 把类的构造器私有化。
* 定义一个类变量用于存储对象。
* 提供一个类方法，保证返回的是同一个对象。
* */
public class SingleDesign2 {
    private static SingleDesign2 sd;

    private SingleDesign2() {
    }

    public static SingleDesign2 get(){
        if(sd == null){
            sd = new SingleDesign2();
        }
        return sd;
    }
}
