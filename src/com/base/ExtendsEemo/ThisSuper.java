package com.base.ExtendsEemo;
/*
* 子类构造器的特点
* 子类的全部构造器，都会先调用父类的构造器，在执行自己。
* 为什么呢？
* 默认情况下，子类的全部构造器的第一行代码都是super() 【写不写都有】,他会调用父类的无参构造器。
* 如果父类没有无参构造器，则必须在子类构造器第一行手写super(...),调用父类的有参数构造器
* 在任意类的构造器中，是可以通过this(...)调用该类的其他构造器的
* this(...)/super(...)都只能放在构造器的第一行，因此两者不能同时出现。
* */
public class ThisSuper {
    public static void main(String[] args) {
        B b = new B();
    }
}

class A{
//    public A() {
//        System.out.println("先执行父类构造器");
//    }
    public A(String name) {
        // this(...)调用该类的其他构造器的
        this(name,18);
        System.out.println("先执行父类构造器1");
    }
    public A(String name,int age) {
        System.out.println("先执行父类构造器2");
    }
}
class B extends A{
    public B() {
//        super();
// 如果父类没有无参构造器，则必须在子类构造器第一行手写super(...),调用父类的有参数构造器
//        super("test");
        super("test",18);
        System.out.println("再执行子类构造器");
    }
}
