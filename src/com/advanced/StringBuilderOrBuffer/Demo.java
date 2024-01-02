package com.advanced.StringBuilderOrBuffer;
/*
* StringBuilder / StringBuffer【多用户】
* StringBuilder代表可变字符串对象，相当于一个容器，它里面装的字符是可以改变的，就是用来操作字符串的。
* 好处：StringBuilder比String更适合做字符串的修改操作，效率会更高，代码也会更简洁。
* 用法：创建对象
* 常用方法：append()添加数据并返回StringBuilder对象本身
* reverse()将对象的内容反转
* length() 返回对象内容长度
* toString() 将StringBuilder转换为String
*
* 对比String: 性能更好，效率更高，字符串频繁拼接、修改建议使用StringBuilder
*
* StringBuffer跟StringBuilder用法一致，但是StringBuilder是线程不安全的，StringBuffer是线程安全的
*/
public class Demo {
    public static void main(String[] args) {
        // 创建对象
        StringBuilder s1 = new StringBuilder("hello");
        s1.append("world");


        // 支持链式编程
        s1.append("yes").append(" no");
        // 反转
        s1.reverse();
        System.out.println(s1);
        System.out.println(s1.length());

        // 将StringBuilder转换为String
        String s2 = s1.toString();
        System.out.println(s2);

    }
}
