package com.base;
/*
* 什么时候用for ,什么时候用while
* 使用场景
* 一开始不知道循坏几次的情况下使用while，知道循坏几次用for
* 本质上没有区别
*
* do-while先执行后判断
*
* 死循环 for(;;) while(true)
* */
public class ForTest {
    public static void main(String[] args) {
        // 获取1-100奇数的和
        int sum = 0;
        for (int i = 1; i < 100; i+=2) {
            sum += i;
        }
        System.out.println("sum:" + sum);

        double sum1 = 500000;
        double sumPeer = 0.1;
        int count = 0;
        while (sumPeer < sum1){
            sumPeer = sumPeer * 2;
            count++;
        }
        System.out.println("循环次数--"+count);
        System.out.println("比较总数--"+sumPeer);
        System.out.println("比较总数--"+sumPeer / 2);


        int test = 0;
        do{
            System.out.println("先执行"+test);
            test++;
        }while (test < 3); // false 退出循环
    }
}
