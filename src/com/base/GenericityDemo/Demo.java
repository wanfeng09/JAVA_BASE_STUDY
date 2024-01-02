package com.base.GenericityDemo;
/*
* 泛型：定义类、接口、方法，同时声明了一个或者多个类型变量<E>，称为泛型类、泛型接口、泛型方法。
* 作用： 泛型提供了在编译阶段约束所能操作的数据类型，并自动进行检查能力。
* 本质：把具体数据类型作为参数传给类型变量
* 泛型接口：修饰符 interface 接口名<类型变量，类型变量，...>{}
* 类型变量建议用大写字母，常用E/T/K/V
*
* 泛型方法：修饰符<类型变量，类型变量，...> 返回值类型 方法名(形参列表){}
* 通配符?：可以再“使用泛型”的时候代表一些类型
* 泛型的上下限
* 上限：? extends Animal -------- 能接收的必须是Animal或者其子类。
* 下限：? super Animal ----------能接收的必须是Animal或者其父类。
*
* 注意事项
* 泛型擦除：泛型是工作在编译阶段的，一旦编译成class文件，class文件就不存在泛型了
* 泛型不支持基本数据类型，只能支持对象类型（引用数据类型）
* */
public class Demo {
    public static void main(String[] args) {
        List<String> l = new List<>();
        l.add("test1");
        l.add("test2");
        String ele = l.get(0);
        System.out.println(ele);

        B<C> b = new B<>();
    }
}

// 自定义泛型类
class List<E>{
    private Object[] arr = new Object[10];
    private int size;
    public boolean add(E e){
        arr[size++] = e;
        return true;
    }

    public E get(int i){
        return (E) arr[i];
    }
}

class A<E,T>{

}

class C{

}

class B<E extends C>{}
