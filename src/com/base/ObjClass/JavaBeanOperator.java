package com.base.ObjClass;
/*
* 定义操作实体类
* */
public class JavaBeanOperator {
    private JavaBean[] j;// 实体类私有成员变量,获取数组



    public JavaBeanOperator() {
    }

    public JavaBean[] getJ() {
        return j;
    }

    public void setJ(JavaBean[] j) {
        this.j = j;
    }

    public JavaBeanOperator(JavaBean[] j) {
        this.j = j;
    }

    public void printJavaBean(){
        for (int i = 0; i < j.length; i++) {
            JavaBean obj = j[i];
            System.out.println("姓名"+obj.getName());
            System.out.println("得分"+obj.getScore());
            System.out.println("年龄"+obj.getAge());
        }
    }
}
