package com.base.ObjClass;
/*
* 实体类（javaBean）：是一种特殊形式的类
* 这个类中的成员变量都要私有，并且要对外提供相应的getter/setter方法。【右键-generate-getter/setter】
* 类中必须要有一个公共的无参的构造器。【右键-generate-constructor】
* 应用场景：负责数据存取，对数据的处理需要交给其他类完成，以实现数据和数据业务处理相分离
* */
public class JavaBean {
    private String name;
    private double score;

    public JavaBean(String name, double score, int age) {
        this.name = name;
        this.score = score;
        this.age = age;
    }

    public JavaBean() {
    }

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
