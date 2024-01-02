package com.base.StaticDemo;
/*
* static:静态，可以修饰成员变量、成员方法。
* 成员变量按照有无static修饰，分为两种：
* 类变量：有static修饰，属于类，在计算机里只有一份，会被类的全部对象共享
* 实例变量（对象的变量）：无static修饰
* 类名.类变量
* 对象.类变量（不推荐）
* 对象.实例变量
* 应用场景
* 在开发中，如果某个数据只需要一份，且希望能够被共享（访问、修改），则该数据可以定义成类变量来记住。
*
* 类方法：有static修饰的成员方法，属于类
* 实例方法：无static修饰的成员方法，属于对象
* 类名.类方法
* 对象.类方法（不推荐）
* 对象.实例方法
*
* 类方法常见的应用场景【工具类】
* 工具类中的方法都是一些类方法，每个方法都是用来完成一个功能的，工具类是给开发人员共同使用的
* 好处：提高代码复用；调用方便；提高开发效率
*
* 为什么工具类的方法要用类方法，而不用实例方法？
* 实例方法需要创建对象来调用的，此时对象只是为了调用方法，对象占内存，这样会浪费内存
* 类方法，直接用类名调用即可，调用方便，也能节省内存。
* 注意：
* 工具类没有创建对象的需求，建议将工具类的对象进行私有化。【例子：Math】
* 类方法中可以直接访问类成员，不可以直接访问实例成员。
* 实例方法中既可以访问类成员，也可以访问实例成员。
* 实例方法中可以出现this关键字，类方法中不可以出现this关键字。
*
* 补充知识
* main方法(形参怎么传入数据) 【类方法】
* 命令行执行
* javac Demo1.java
* java Demo1 12 66 26 18
* public class Demo1 {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }
    }
}
*
* */
public class Demo1 {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }
        StaticObj s1 = new StaticObj();
        StaticObj s2 = new StaticObj();
        StaticObj s3 = new StaticObj();
        // 类名.类变量
        System.out.println(StaticObj.number); // 3

        StaticObj.classMed(); // 类方法【通过类名访问】
        s1.ObjMed(); // 实例方法【通过对象方法】

//        ToolClass t1 = new ToolClass(); // 私有化了，不允许创建对象
        ToolClass.toolMed();

        // 静态代码块
//        BlockDemo b1 = new BlockDemo();
        BlockDemo.test();

        // 单例设计模式
        SingleDesign sd1 = SingleDesign.get();
        SingleDesign sd2 = SingleDesign.get();
        System.out.println(sd1 == sd2); // 指向同一个内存地址

        SingleDesign2 sd3 = SingleDesign2.get();
        SingleDesign2 sd4 = SingleDesign2.get();
        System.out.println(sd3 == sd4); // 指向同一个内存地址
    }

}
