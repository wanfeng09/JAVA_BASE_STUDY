package com.advanced.StringJoinerMathSystem;

import java.util.StringJoiner;

/*
* StringJoinerJDK8开始才有,跟StringBuilder一样也是用来操作字符串的，也可以看成是一个容器，创建之后里面的内容是可变的
* 好处：不仅能提高字符串的操作效率，并且在有些场景下使用它操作字符串，代码会更简洁。
* 创建对象，指定拼接时的（间隔符号,开始符号，结束符号）
* 常用方法
* add()添加对象，并返回对象本身
* length()返回长度
* toString()返回一个字符串（拼接后的接口）
*
* Math常用方法
* abs() 获取参数额绝对值
* ceil() 向上取整
* floor() 向下取整
* round() 四舍五入
* max() 获取两个值的最大值。
* pow()返回a的b次幂的值
* random()随机数
*
* System代表程序所在的系统，也是一个工具类
* 常用方法
* exit()终止当前运行的Java虚拟机【参数：非0代表异常终止】--人为终止
* currentTimeMillis() 返回当前系统的时间毫秒值形式【返回long类型，1970-1-1 0:0:0开始】---可以测试程序的性能分析
* */
public class Demo {
    public static void main(String[] args) {
        StringJoiner s = new StringJoiner(",","[","]");
        s.add("a");
        s.add("b");
        s.add("c");
        System.out.println(s);

        // 终止当前运行的Java虚拟机
//        System.exit(0);
       long time =  System.currentTimeMillis();
        System.out.println(time); // 1701054830935
        // Math常用方法
        System.out.println(Math.abs(-12));
        System.out.println(Math.ceil(9.6));
        System.out.println(Math.floor(9.6));
        System.out.println(Math.round(9.6));
        System.out.println(Math.max(2,10));
        System.out.println(Math.pow(2,3));
        System.out.println(Math.random()); // 返回0.0跟1.0之间的小数，包前不包后
    }
}
