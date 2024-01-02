package com.unitOrReflection.Annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*
* 模拟Junit框架
* 需求：定义若干个方法，只要加了自定义注解，就会触发改方法执行
*
* 1、定义一个自定义注解，现在注解方法，存活范围是一直都在
* 2、定义若干个方法，部分方法加上自定义注解修饰，部分方法不加
* 3、模拟一个junit程序，可以触发加自定义注解的方法执行
*
* */
public class MethodTest {
    @MethodAnnotate
    public void test1(){
        System.out.println("test1执行----");
    }
    public void test2(){
        System.out.println("test2执行----");
    }
    public void test3(){
        System.out.println("test3执行----");
    }
    @MethodAnnotate
    public void test7(){
        System.out.println("test7执行----");
    }
    public void test4(){
        System.out.println("test4执行----");
    }
    @MethodAnnotate
    public void test6(){
        System.out.println("test6执行----");
    }

    public static void main(String[] args) throws Exception{
        MethodTest methodTest = new MethodTest();
        // 启动
        Class c = MethodTest.class;
        // 提取这个类中的全部成员方法
        Method[] m = c.getDeclaredMethods();
        // 遍历这个方法，看方法上是否存在注解，然后触发执行
        for (Method method: m){
            if(method.isAnnotationPresent(MethodAnnotate.class)){
                method.invoke(methodTest);
            }
        }
    }
}
