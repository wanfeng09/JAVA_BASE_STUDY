package com.base.InnerClass;
/*
* 内部类：是类中的五大成分之一（成员变量，方法，构造器，内部类，代码块）
* 如果一个类定义在另一个类的内部，这个类就是内部类。
* 应用场景
* 当一个类的内部，包含了一个完成的事物，且这个事物没有必要单独设计是，就可以把这个事物设计成内部类
* 内部类四种形式
* 1、成员内部类：就是类中的一个普通成员，类似成员变量、成员方法【JDK16 以上才支持静态成员变量】
* 创建对象格式
* 外部类名.内部类名 对象名 = new 外部类().new 内部类();
* 可以直接访问外部类的实例成员、静态成员。
* 在内部类的实例方法中，拿到外部类的对象，格式是：外部类名.this
* 2、静态内部类：有static修饰的内部类，属于外部类自己持有。
* 创建对象格式
* 外部类名.内部类名 对象名 = new 外部类.new 内部类();
* 可以直接访问外部类的静态成员，不可以直接访问外部类的实例成员。
* 3、局部内部类：定义在方法中，代码块中、构造体等执行体中。【了解即可，不重要】
* 4、匿名内部类：特殊的局部内部类；不需要声明类名字.
* 特点：匿名内部类本质就是一个子类，并会立即创建出一个子类对象。
* 作用：用于更方便的创建一个子类对象。
* 匿名内部类通常作为一个参数传输给方法。
* */
public class Demo {
    public static void main(String[] args) {
        // 访问成员内部类
        Outer.Inner in = new Outer().new Inner();
        in.test();

        // 访问静态内部类
        StaticOuter.StaticInner sin = new StaticOuter.StaticInner();
        sin.test();
        StaticOuter.test2();

        // 匿名内部类
        OuterAnonymous o = new OuterAnonymous() {
            @Override
            public void test() {
                System.out.println("子类重写方法");
            }
        };
        o.test();
    }
}

abstract class OuterAnonymous{
    public abstract void test();
}

// 成员内部类
class Outer{
    private int age = 99;
    class Inner{
        private String name;
        private int age = 88;

        // JDK16 以上才支持静态成员变量
//        private static String name;
        public  void  test(){
            int age = 66;
            System.out.println(age); // 66
            System.out.println(this.age); // 88
            System.out.println(Outer.this.age); // 99
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

// 静态内部类
class StaticOuter{
    static String name = "test1";
    int age = 20;
    static class StaticInner{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void test(){
            System.out.println(name);
            System.out.println(StaticOuter.name); // 可以直接访问外部类的静态成员
//            System.out.println(StaticOuter.age); // 不可以直接访问外部类的实例成员。
        }
    }
     public static void  test2(){
         System.out.println(name);
     }
}

// 局部内部类
class PartOuter{
    public static void test(){
        class PartInner{

        }
        abstract class PA{

        }
        // JDK16以上
//        interface PC{
//
//        }
    }
}
