package com.base.GenericityDemo;

/*
* 泛型方法：修饰符<类型变量，类型变量，...> 返回值类型 方法名(形参列表){}
* 上限：? extends Animal -------- 能接收的必须是Animal或者其子类。
* 下限：? super Animal ----------能接收的必须是Animal或者其父类。
* */


import java.util.ArrayList;

public class Med1 {
    public static void main(String[] args) {
        String a1 = test("测试");
        System.out.println(a1);
        Animal1 a2 = test(new Animal1());

        ArrayList<Animal1> a3 = new ArrayList<>();
        a3.add(new Animal1());
        a3.add(new cat());

        ArrayList<cat> a4 = new ArrayList<>();
        a4.add(new cat());
    }

    public static <M> M test(M m){
        return m;
    }
    public static void go(ArrayList<? extends Animal1> arr){

    }
//    public static <T extends Animal1> void go(ArrayList<T> arr){
//
//    }
}

class Animal1{

}

class cat extends Animal1 {

}

class dog extends Animal1{

}
