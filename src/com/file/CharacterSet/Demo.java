package com.file.CharacterSet;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/*
* 标准ASCII字符集
* ASCII：美国信息交换标准代码，包含了英文、数字、符号等，占1个字节
* 标准ASCII使用1个字节存储一个字符，首尾是0，总共128个字符。
* GBK 汉字内码扩展规范，国际
* 汉字编码字符集，包含了2万多个汉字等字符，GBK中一个中文符编码成两个字节的形式存储
* GBK兼容了ASCII字符集 【汉字的第一个字节的第一位是1，英文是0】 （汉字2个字节 1xxxxx xxxxxx 英文、数字1个字节 0xxxxx ）
*
* Unicode字符集（统一码，也叫万国码）
* Unicode是国际组织制定的，可以容纳世界上所有文字，符号的字符集
* UTF-32 4个字节表示一个字符【奢侈，占存储空间，通信效率变低】（xxxxx xxxxx xxxxx xxxxx）
* 解决 UTF-8 【推荐】
* UTF-8是Unicode字符集的一种编码方案，采取可变长的编码方案: 1/2/3/4个字节
* 英文字符、数字等只占1个字节（兼容标准ASCII编码），汉字字符占用3个字节
* 1个字节 0xxxxx
* 2个字节 110xxxxx 10xxxxx
* 3个字节 1110xxxxx 10xxxxx 10xxxxx
* 4个字节 11110xxxxx 10xxxxx 10xxxxx 10xxxxx
*
* 字符编码是使用的字符集，和解码时使用的字符集必须一致，否则会出现乱码
* 英文、数字一般不会出现乱码，因为很多字符集都兼容了ASCII编码
*
* 对字符编码
* byte[] getBytes() 使用平台默认字符集将该String编码为一系列字节，将结果存储到新的字节数组中
* byte[] getBytes(String charsetName) 指定字符集编码
* 对字符解码
* 文档 -- docs/api/java/lang/String.html
* String(byte[] bytes) 通过使用平台的默认字符集解码指定的字节数组来构造新的String
* String(byte[] bytes,String charsetName) 指定字符集解码
*
*
* */
public class Demo {
    public static void main(String[] args) throws Exception {
        // 编码 平台UTF-8
        String str = "hello 世界";
        byte[] bytes = str.getBytes();
        System.out.println(Arrays.toString(bytes)); // [104, 101, 108, 108, 111, 32, -28, -72, -106, -25, -107, -116]

        // 解码
        String str1 = new String(bytes);
        System.out.println(str1); // hello 世界

        // 指定字符集【乱码】
        String str2 = new String(bytes,"GBK"); // 抛出异常 Exception
        System.out.println(str2); // hello 涓栫晫 【因为字节码不一致】

        // 乱码 【获取同一个字符集编码解码】
        byte[] bytes1 = str.getBytes("GBK");
        String str3 = new String(bytes1,"GBK");
        System.out.println(str3); // hello 世界

    }
}
