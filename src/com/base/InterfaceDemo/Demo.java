package com.base.InterfaceDemo;
/*
 * interface关键字定义接口
 * 接口里只能有成员变量（默认：常量）、成员方法（默认：抽象方法）
 * 接口不能创建对象，接口是用来被类实现的（implements）的，实现接口的类称为实现类。
 * 一个类可以实现多个接口，必须重写完全部接口的全部抽象方法，否则实现类需要定义成抽象类
 *
 * 好处
 * 弥补了类单继承的不足，一个类同时可以实现多个接口
 * 让程序可以面向接口编程，可以灵活方便的切换各种业务实现。
 *
 * JDK8新增
 * 默认方法：必须使用default修饰，默认会被public修饰[实例方法：对象的方法]
 * JDK9新增
 * 私有方法：必须使用private修饰[实例方法：对象的方法]
 * 静态方法：必须使用static修饰，默认会被public修饰[静态方法：类方法]
 *
 * 好处：增强了接口的能力，便于项目的扩展和维护
 *
 * 注意事项：
 * 一个接口继承多个接口，如果多个接口存在方法签名冲突，则此时不支持多继承。
 * 一个类实现多个接口，如果多个接口存在方法签名冲突，则此时不支持多实现。
 * 一个类继承（extends）了父类（亲爸），又同时实现（implements）了接口（干爹），父类中和接口中都有同名的默认方法，实现类会优先用父类的。
 * 一个类实现了多个接口，多个接口中存在同名的默认方法（default），可以不冲突，这个类重写该方法即可。
 * */
public interface Demo {
    double a = 3.14; // 等同于 public static final double a = 3.14;
    void test(); // 等同于 public abstract void test();


    // 必须使用default修饰，默认会被public修饰
    default void test2(){
        System.out.println("默认方法");
    }

    // JDK9才支持
//    private void test3(){
//
//    }

    // 静态方法
    static void  test6(){
        System.out.println("静态方法");
    }

}

interface Demo1{
    void test1();
}
