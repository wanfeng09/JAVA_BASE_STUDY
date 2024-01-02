package com.unitOrReflection.Annotation;
/*
 * 注解（Annotation）
 * 就是java代码里的特殊标记，比如：@Override、@Test等，作用是：让其他程序根据注解信息来决定怎么执行该程序
 * 注解可以用在类上、构造器上、方法上、成员变量上、参数上等位置处。
 *
 * 自定义注解
public @interface 注解名称 {
    public 属性类型 属性名();
    public 属性类型 属性名() default 默认值;
}
 * 特殊属性名：value
 * 如果注解中只有一个value属性，使用注解时，value名称可以不写
 *
 *
 * 原理
 * 注解本质是一个接口，java所有注解都是继承了Annotation接口的
 * @注解(...): 其实就是一个实现类对象，实现了该注解以及Annotation接口
 *
 * 元注解
 * 修饰注解的注解
 * @Target(ElementType.TYPE)
 * 声明被修饰的注解只能在哪些文职使用
 * TYPE：类、接口
 * FIELD：成员变量
 * METHOD：成员方法
 * PARAMETER：方法参数
 * CONSTRUCTOR：构造器
 * LOCAL_VARIABLE：局部变量
 *
 * @Retention(RetentionPolicy.RUNTIME)
 * 声明注解的保留周期
 * SOURCE：源码阶段，字节码文件中不存在
 * CLASS：保留到字节码文件阶段，运行阶段不存在
 * RUNTIME：一直保留运行阶段
 *
 * AnnotatedElement接口提供了解析注解的方法
 * getDeclareAnnotations() 获取当前对象上面的注解
 * getDeclareAnnotations(Class<T> annotationClass) 获取指定的注解对象
 * isAnnotationPresent(Class<Annotation> annotationClass) 判断当前对象上是否存在某个注解
 *
 *
 *
 *
 * */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// @Target(ElementType.TYPE) // 当前被修饰的注解只能用在类上
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME) // 声明注解一直保留运行阶段
public @interface Demo {
    String name();
    boolean flag() default true;
    String[] list();
}
