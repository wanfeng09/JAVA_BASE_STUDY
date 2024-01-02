package com.unitOrReflection.ReflectionTest;

import java.lang.reflect.Constructor;

/*
* 反射（Reflection）
* 反射就是：加载类，并允许以编程的方式解剖类中的各种成分（成员变量、方法、构造器等）
* 1、记载类，获取类的字节码：Class对象【类名.class / forName(包+类名) / 对象.getClass()】
* 2、获取类的构造器：Constructor对象
* 3、获取类的成员变量：Field对象
* 4、获取类的成员方法：Method对象
*
* Class提供了从类中获取构造器的方法
* getConstructors() 获取全部构造器【只能获取public修饰的】
* getDeclaredConstructors() 获取全部构造器【无视修饰符】
* getConstructors(具体参数) 获取某个构造器【只能获取public修饰的】
* getDeclaredConstructors(具体参数) 获取某个构造器【无视修饰符】
*
* Constructor获取构造器的作用：初始化对象返回
* 方法
* newInstance(obj...) 调用此构造器对象表示的构造器，并传入参数，完成对象的初始化并返回
* setAccessible(boolean b) 设置true，表示禁止检查访问控制（暴力反射）
*
* */
public class ClassDemo {
    public static void main(String[] args) throws Exception {
        // 获取class对象
        // 方式1：类名.class
        Class c = Cat.class;
        // 方式2：forName(包+类名)
        Class c2 = Class.forName("com.unitOrReflection.ReflectionTest.Cat");
        // 方式3：对象.getClass()
        Cat cat = new Cat();
        Class c3 = cat.getClass();
        System.out.println(c == c2); // true
        System.out.println(c2 == c3); // true

        // 包+类名
        System.out.println(c.getName()); // com.unitOrReflection.ReflectionTest.Cat
        // 类名
        System.out.println(c.getSimpleName()); // Cat


        System.out.println("------------------");
        // 反射第一步：先获取class对象
        Class a = Animal.class;
        // 获取类的全部构造器
        // Constructor[] constructors = a.getConstructors(); // 只能获取public修饰的
        Constructor[] constructors = a.getDeclaredConstructors(); // 获取全部构造器,无视修饰符
        // 遍历数组中的每个构造器
        for (Constructor constructor:constructors){
            // getName() 构造器包名+类名
            // getParameterCount() 构造器参数数量
            System.out.println(constructor.getName() + "----" + constructor.getParameterCount());
        }
        System.out.println("------------------");
        // 获取有参数构造器
        Constructor constructorHas = a.getDeclaredConstructor(int.class,String.class);
        System.out.println(constructorHas.getName() +  "----" + constructorHas.getParameterCount());

        // 设置true，表示禁止检查访问控制（暴力反射）
        constructorHas.setAccessible(true);
        // 调用此构造器对象表示的构造器，并传入参数，完成对象的初始化并返回
        Animal a2 = (Animal) constructorHas.newInstance(1,"猫");
        System.out.println(a2.getName());

    }
}

class Cat{

}

class Animal{
    public String type;
    private int age;
    private String name;

    private Animal(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public Animal() {
    }

    public Animal(String type, int age, String name) {
        this.type = type;
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
