package com.file.Recursion;
/*
* 递归算法
* 直接递归：自己调用自己
* 间接递归：方法调用其他方法，其他方法又回调方法自己
* 递归如果没有控制好终止，会出现死循环，导致栈内存溢出错误-StackOverflowError
*
*
* */
public class Demo {
    public static void main(String[] args) {
        // test(); // StackOverflowError 栈内存溢出错误
        // 计算n的阶乘 3的阶乘 1*2*3
        // f(n) = f(n - 1)*n
        System.out.println(f(3)); // 6
    }
    public static int f(int n){
        if(n == 1){
            return 1;
        }else{
            return f(n - 1)*n;
        }
    }
    // 直接递归
    public static void test(){
        test();
    }
    // 间接递归
    public static void test2(){
        test3();
    }

    public static void test3(){
        test2();
    }
}
