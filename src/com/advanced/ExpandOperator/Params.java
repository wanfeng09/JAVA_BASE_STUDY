package com.advanced.ExpandOperator;

import java.util.Arrays;

/*
* 可变参数
* 特殊形参，定义在方法、构造器的形参列表里，格式：数据类型...参数名称
* 特点：可以不传数据，传一个或多个数据给它，也可以传数组
* 好处：常常用来灵活的接收数据
*
* 注意
* 可变参数在方法内部就是一个数组
* 一个形参列表中可变参数只能有一个
* 可变参数必须放在形参列表的最后面
*
*
* */
public class Params {
    public static void main(String[] args) {
        // test(3);
        test(1,2,3,4,5,6,6,7);
    }

    public static void test(int age,int...params){
        System.out.println(age); // 1
        System.out.println(params.length); // 7
        System.out.println(Arrays.toString(params)); // [2, 3, 4, 5, 6, 6, 7]
    }
}
