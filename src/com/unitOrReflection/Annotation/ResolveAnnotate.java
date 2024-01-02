package com.unitOrReflection.Annotation;
/*
* 解析注解的案例
* 1、定义注解，要求：限制注解的使用位置（类和成员方法上），限制注解的有效范围（一直到运行时）
* 2、定义一个类，在类中定义一个方法，使用注解
* 3、定义测试类，解析类中的全部注解
* */
import org.junit.Test;

import java.lang.reflect.Method;

public class ResolveAnnotate {
    @Test
    public void  test() throws Exception {
        Class c = com.unitOrReflection.Annotation.Test.class;
        Method m = c.getDeclaredMethod("medTest");
        System.out.println(m.getName());
        // 解析类上的注解
        // 判断类上是否包含某个注解
        if(c.isAnnotationPresent(Demo.class)){
            Demo d = (Demo) c.getDeclaredAnnotation(Demo.class);
            System.out.println(d.flag());
            System.out.println(d.name());
            System.out.println(d.list());
        }
    }
}
