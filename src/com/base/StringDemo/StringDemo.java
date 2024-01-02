package com.base.StringDemo;

/*
 * String创建对象封装字符串数据的方式
 * java程序中的所有字符串文字都为此类对象，直接双引号 ""
 * 调用String类构造器初始化字符串对象，new String类
 *
 * 注意
 * String对象是不可变字符串对象
 * 为什么？
 * 栈内存: 变量【记录内存地址】
 * 堆内存：字符串常量池【记录值】，字符串改变会产生新的内存地址，从而替换变量【栈内存所记录的内存地址】。
 *
 * 只要以""方式写出的字符串对象，会存储到字符串常量池，且相同内容的字符串只存储一份【节约内存】
 * 但通过new方式创建字符串对象，每new一次都会产生一个新的对象放在堆内存中
 * */
public class StringDemo {
    public static void main(String[] args) {
        String s = "测试数据";
        // 获取字符串长度
        int len = s.length();
        // 获取字符串某个索引的字符
        char c = s.charAt(1);
         System.out.println(len+"---"+c);
        // 字符串遍历
//        for (int i = 0; i < s.length();i++){
//            char ch = s.charAt(i);
//            System.out.println(ch);
//        }

        // 把字符串转换成字符串数组，在进行遍历
//        char[] chars = s.toCharArray();
//        for (int i = 0; i < chars.length; i++) {
//            System.out.println(chars[i]);
//        }
        // 只要以""方式写出的字符串对象，会存储到字符串常量池，且相同内容的字符串只存储一份
        String ss1 = "demo1";
        String ss2 = "demo1";
        System.out.println("-----------------------");
        System.out.println(ss1 == ss2); // 指向同一个内存地址
//        String s1 = new String("demo1");
//        String s2 = new String("demo1");
//        System.out.println(s1 == s2); // false【判断内存地址】
//        System.out.println(s1.equals(s2)); //true 判断字符串内容比较
//        String s3 = new String("Demo1");
//        String s4= new String("demo1");
//        System.out.println(s3.equals(s4)); //false 判断字符串内容比较
//        System.out.println(s3.equalsIgnoreCase(s4));//true 忽略大小写比较

        // 截取字符串内容【包前不包后】
        String js = s.substring(0,2);
        System.out.println(js); // 测试
        System.out.println(s.substring(2)); // 数据

        // 字符串替换
        String ts = s.replace("测试","替换");
        System.out.println(s); // 测试数据
        System.out.println(ts); // 替换数据

        // 判断字符串是否包含某个关键字
//        System.out.println(s.contains("测试"));
//        System.out.println(s.contains("替换"));

        // 判断字符串是否以某个字符串开头
//        System.out.println(s.startsWith("测试"));
//        System.out.println(s.startsWith("替换"));

        // 把字符串指定字符分割成数组
        String[] sarr = s.split("");
        for (int i = 0; i < sarr.length; i++) {
            System.out.println(sarr[i]);
        }


    }
}
