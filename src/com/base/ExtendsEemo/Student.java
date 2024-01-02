package com.base.ExtendsEemo;

public class Student extends People{
    String str = "child";
    private void med1(){
        System.out.println("私有");
    }
    void med(){
        System.out.println("缺省");

    }
    protected void med3(){
        System.out.println("保护");

    }
    public void  med6(){
        System.out.println("公共");
    }

    public void test(){
        String str = "my";
        System.out.println(str);
        System.out.println(this.str);
        System.out.println(super.str);
    }
}
