package com.base.ObjClass;
/*
 * 构造器
 * 类名跟方法名必须一致，分为无参数构造器跟有参数构造器[可以方法重载]
 * 特点：创建对象时，对象会去调用构造器。
 * 使用场景：对象初始化
 * 注意：
 * 类在设计时，如果不写构造器，java是会为类自动生成一个无参构造器。
 * 一旦定义了有参数构造器，java就不会自动生成无参数构造器了，此时建议自己手写一个无参数构造器出来。
 * */
public class StudentConstructor {
    private StudentObj student;
    // 无参数构造器
    public StudentConstructor() {
        System.out.println("无参数构造器打印");
    }
    // 有参数构造器
    public StudentConstructor(String name) {
        System.out.println("有参数构造器");
    }

    public StudentConstructor(StudentObj student) {
        this.student = student;
    }

    public void printInfo() {
        int sex = this.student.getSex();
        String str = sex == 0 ? "男士" : "女士";
        System.out.println("学生信息---"+this.student.name+"学生性别---"+ str);
    }
}
