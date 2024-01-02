package com.base.ExtendsEemo;

public class People {
    private String name;
    String str = "father";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printInfo(){
        System.out.println("方法重写");
    }
}
