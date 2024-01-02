package com.base.EnumDemo;
/*
* 枚举: 是一种特殊类。
* 第一行必须罗列枚举对象的名字,本质是常量，并且每个常量记住的都是枚举类的一个对象。
* 枚举类的构造器都是私有的，因此枚举类不能创建对象。
* 枚举类都是最终类，不可以被继承
* 枚举类第二行可以定义类的其他成员
* 编译器为枚举类新增了几个方法，并且枚举类都是继承：java.lang.Enum类的，从enum类也会继承一些方法。
* 反编译 javap Demo.class
*
* 抽象枚举，有抽象方法，第一行构建对象名称需重写方法
* 使用枚举类实现单例模式
* */
public enum Demo {
    A,B;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

enum ABDemo{
    A(){
        @Override
        public void test() {

        }
    }, B("测试有参构造器"){
        @Override
        public void test() {
            System.out.println(getName());
        }
    };
    public abstract void test();
private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    ABDemo(String name) {
        this.name = name;
    }

    ABDemo() {
    }
}


enum Single{
    A;// 单例模式
}


//class List{
//    public static final int BOY = 0;
//    public static final int GIRL = 1;
//}

// 以上优化
enum List{
BOY,GIRL
}
