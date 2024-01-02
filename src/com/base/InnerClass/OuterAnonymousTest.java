package com.base.InnerClass;

public class OuterAnonymousTest {
    public static void main(String[] args) {
        // 匿名内部类
    go(new Animal() {
        @Override
        public void test() {
            System.out.println("---------------");
        }
    });
    }

    public static void go(Animal a){
        a.test();
        System.out.println(Animal.name);
    }
}

interface Animal{
String name="常量";
void test();
}
