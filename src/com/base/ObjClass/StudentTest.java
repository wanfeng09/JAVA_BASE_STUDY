package com.base.ObjClass;
/*
* 面向对象的三大特征：封装，继承，多态
* 封装思想：决定属性和行为归属谁的问题
* 如何更好的封装：成员变量最好私有化，只能本类访问
* 合理暴露（getter/setter）*/
public class StudentTest {
    public static void main(String[] args) {
        StudentObj s = new StudentObj();
        s.name = "小明";
        System.out.println("学生姓名: "+s.name);
        s.setSex(0); // 设置性别
        System.out.println(s.getSex());

        StudentConstructor sc = new StudentConstructor(s);
        sc.printInfo();

    }
}
