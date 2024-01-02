package com.base.StaticDemo;

public class StaticObj {
    static int number;

    public StaticObj() {
        number++;
    }

    public void ObjMed(){
        System.out.println("实例方法");
    }

    public  static void classMed(){
        System.out.println("类方法");
    }
}
