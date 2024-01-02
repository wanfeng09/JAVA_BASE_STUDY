package com.base.GenericityDemo;

public class Test2 {
    public static void main(String[] args) {

    }
}

class Animal{

}
class Cat implements Test1<Animal>{
    @Override
    public void add(Animal animal) {

    }

    @Override
    public Animal test() {
        return null;
    }
}
