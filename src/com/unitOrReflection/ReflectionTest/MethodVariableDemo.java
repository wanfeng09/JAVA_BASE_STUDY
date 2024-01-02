package com.unitOrReflection.ReflectionTest;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/*
* Class提供了从类中获取成员方法的方法
* 方法
* getMethods() 获取类的全部成员方法（只能获取public修饰的）
* getDeclareMethods() 获取类的全部成员方法 （无视修饰符）
* getMethods(String name,class<?>...paramType) 获取类的某个成员方法（只能获取public修饰的）
* getDeclareMethods(String name,class<?>...paramType) 获取类的某个成员方法（无视修饰符）
*
* 成员方法的作用：执行
* invoke(Object obj,Object...args) 触发某个对象的该方法执行
* setAccessible(boolean flag) 设置true，表示禁止检查访问控制（暴力反射）
*
* Class提供了从类中获取成员变量的方法
* 方法
* getFields() 获取类的全部成员变量（只能获取public修饰的）
* getDeclareFields() 获取类的全部成员变量（无视修饰符）
* getFields(String name) 获取类的某个成员变量（只能获取public修饰的）
* getDeclareFields(String name) 获取类的某个成员变量（无视修饰符）
*
* 获取到成员变量的作用：赋值、取值
* set(Object obj,Object value) 赋值
* get(Object obj) 取值
* setAccessible(Boolean flag) 设置为true，表示禁止检查访问控制（暴力反射）
*
* */
public class MethodVariableDemo {
    public static void main(String[] args) throws Exception {
        Class c = Cat2.class;
        // 获取累的全部成员方法
        Method[] methods = c.getDeclaredMethods();
        for (Method m : methods){
            System.out.println(m.getName() + "------" + m.getParameterCount() + "------" + m.getReturnType());
        }

        // 获取某个方法对象
        // 无参数
        Method sleep = c.getDeclaredMethod("sleep");
        System.out.println(sleep.getName());
        //有参数
        Method eat = c.getDeclaredMethod("eat",String.class);
        System.out.println(eat.getName()+ "------" + eat.getParameterCount() + "------" + eat.getReturnType());
        Cat2 cat3 = new Cat2();
        String s3 = (String) eat.invoke(cat3,"鱼");
        System.out.println(s3);

        System.out.println("--------------");
        // 获取类的全部成员变量
        Field[] fields = c.getDeclaredFields();
        for (Field field: fields){
            System.out.println(field.getName() + "----" + field.getType());
        }
        System.out.println("--------------");
        // 定位某个成员变量
        Field f1 = c.getDeclaredField("name");
        System.out.println(f1.getName()+ "----" + f1.getType());

        // 赋值
        Cat2 cat2 = new Cat2();
        f1.setAccessible(true);
        f1.set(cat2,"布偶");

        // 取值
        String s2 = (String) f1.get(cat2);
        System.out.println(s2); // 布偶

    }
}

class Cat2{
    private String name;
    private static final int COUNT = 166;

    public String name2;
    private int age;
    public int age2;
    public String eat(String name){
        return "猫在吃" + name + "~";
    }
    public void sleep(){
        System.out.println("猫在睡觉~");
    }

    public Cat2() {
    }

    public Cat2(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Cat2(String name, String name2, int age, int age2) {
        this.name = name;
        this.name2 = name2;
        this.age = age;
        this.age2 = age2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
