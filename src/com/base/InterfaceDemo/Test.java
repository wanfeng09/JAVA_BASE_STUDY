package com.base.InterfaceDemo;

public class Test {
    public static void main(String[] args) {
        A a = new A();
        a.test();
        a.test2();
        // 类方法
        Demo.test6();
    }

}

class A implements Demo,Demo1{

    @Override
    public void test() {

    }

    @Override
    public void test1() {

    }
}
