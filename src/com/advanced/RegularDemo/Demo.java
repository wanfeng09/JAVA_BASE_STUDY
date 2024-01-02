package com.advanced.RegularDemo;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
* 正则表达式
* 文档下载地址
* jdk8版本文档https://www.oracle.com/java/technologies/javase-jdk8-doc-downloads.html
* 查看文档路径 /jdk-8u391-docs-all/docs/api/java/util/regex/Pattern.html
* 由一些特定的字符组成，代表的是一个规则
* 作用
* 效验数据格式是否合法
* 在一段文本中查找满足要求的内容
*
* 基本使用
* 注意以下只能匹配单个字符
* [abc] 只能匹配abc
* [^abc] 不能是abc
* [a-zA-Z] 只能是a-z A-Z字符
* [a-z&&[^bc]] a-z,除了bc
* [a-zA-z0-9] 只能是a-z A-Z 0-9字符
*
* 搜索替换replaceAll(正则,newStr)
* 分割内容split(正则)：返回一个字符串数组
*
* */
public class Demo {
    public static void main(String[] args) {
        System.out.println("ac".matches("[abc]")); // false
        System.out.println("a".matches("[abc]")); // true
        System.out.println(checkQQ("266563")); // true

        // 判断email
        String email = "22@qq.com";
        System.out.println(email.matches("\\w{2,}@\\w{2,20}(\\.\\w{2,10}){1,2}")); // true

        // 判断手机号 13256235613 010-13256235613 01013256235613
        String phone = "13256235613";
        System.out.println(phone.matches("(1[3-9]\\d{9})|(0\\d{2,7}-?[1-9]\\d{4,19})"));
        System.out.println("------------------------------");
        // 数据爬取 email、手机号
        String data = "数据测试\n" + "电话: 1866668888，18699997777\n" + "或者联系邮箱: boniu@itcast.cn，in" + "热线电话: 400-618-9090 ，400-618-4000，4006184000，4006189090";
        String regex = "(\\w{2,}@\\w{2,20}(\\.\\w{2,10}){1,2})|(1[3-9]\\d{9})|(0\\d{2,7}-?[1-9]\\d{4,19})";
        // 把正则表达式封装成一个Pattern对象
        Pattern p = Pattern.compile(regex);
        // 通过Pattern对象去获取查找内容的匹配器对象
        Matcher m = p.matcher(data);
        // 定义一个循环开始爬取信息
        while (m.find()){ // 返回布尔值
            String rs = m.group();
            System.out.println(rs);
        }
        System.out.println("------------------------------");

        String s1 = "年-月-日";
        System.out.println(s1.replaceAll("-","/")); // 年/月/日
        // 去重
        String s2 = "HHHelllo WWWWWorld";
        System.out.println(s2.replaceAll("(.)\\1+","$1")); // Helo World
        // 分割 【提取中文】
        String s3 = "数据1hello90测试2dsdsdsds世界3你好";
        String[] sarr = s3.split("\\w+");
        System.out.println(Arrays.toString(sarr)); // [数据, 测试, 世界, 你好]

    }
    public static boolean checkQQ(String qq){
        // 效验qq号，要求全部是数字，长度（6-20）之间，不能以0开头
        return qq != null && qq.matches("[1-9]\\d{5,19}");
    }



}
