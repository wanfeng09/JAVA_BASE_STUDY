package com.base.ObjClass;
/*
* 成员变量和局部变量区别
* 区别|类中位置|初始化值|内存位置|作用域|生命周期
* 成员变量|类中、方法外|有默认值，不建议赋值|堆内存|整个对象|与对象共存亡
* 局部变量|常见于方法中|无默认值、必需赋值|栈内存|所属大括号|方法调用与结束*/
public class JavaBeanTest {
    public static void main(String[] args) {
        JavaBean[] jArr = new JavaBean[3];
        jArr[0]= new JavaBean("a",88,17);
        jArr[1]= new JavaBean("b",100,22);
        jArr[2]= new JavaBean("c",81,18);
        JavaBeanOperator jo = new JavaBeanOperator(jArr);
        jo.printJavaBean();
    }
}
