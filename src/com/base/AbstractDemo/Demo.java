package com.base.AbstractDemo;

/*
 * abstract抽象类，可以修饰类、成员方法
 * abstract修饰类，这个类就是抽象类；
 * 修饰方法，这个方法就是抽象方法
 * 抽象类中不一定有抽象方法，有抽象方法的类一定是抽象类
 * 类该有的成员（成员变量，方法，构造器）抽象类都可以有
 * 特点
 * 抽象方法只有方法签名，不能写方法体。
 * 抽象类不能创建对象，仅作为一种特殊的父类，让子类继承并实现。
 * 一个类继承抽象类，必须重写完抽象类的全部抽象方法，否则这个类也必须定义成抽象类
 *
 * 应用场景
 * 父类知道每个子类都要做某个行为，但每个子类要做的情况不一样，父类定义成抽象方法，交给子类去重写实现，这样设计就是为了能更好的支持多态。
 * */
public class Demo {
    // 抽象方法只有方法签名，不能写方法体。
    public static void main(String[] args) {
        Animal a1 = new Cat();
        a1.test();
    }
}

abstract class Animal {
    public abstract void test();

}

class Cat extends Animal {
    public void test(){
        System.out.println("喵喵喵~~~~");
    }
}

class Dog extends Animal {
    public void test(){
        System.out.println("汪汪汪~~~~");
    }
}
