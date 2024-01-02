package com.file.IO;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/*
* 字符缓冲流
* 对集合进行排序
* 遍历集合写入到新文件中
*
*
* 测试a.txt数据

3.排序测试3
6.排序测试6
2.排序测试2

1.排序测试1
8.排序测试8
*
* */
public class BufferTest {
    public static void main(String[] args) {
        // arrayListBufferChar();

    }

    public static void arrayListBufferChar(){
        try(
                BufferedReader reader = new BufferedReader(new FileReader("D:\\java\\demo\\IOtest\\a.txt"));
                BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\java\\demo\\IOtest\\b.txt"));
                ) {
            // 定义一个ArrayList集合存储每段内容【有序、可重复、有索引】
            List<String> list = new ArrayList<>();
            // 按照行读取内容，存入集合中
            String line;
            while ((line = reader.readLine()) != null){
                list.add(line);
            }
            System.out.println(list); // [3.排序测试3, , 6.排序测试6, 2.排序测试2, 1.排序测试1, 8.排序测试8]
            // 进行排序
            Collections.sort(list);
            System.out.println(list); // [, 1.排序测试1, 2.排序测试2, 3.排序测试3, 6.排序测试6, 8.排序测试8]

            // 遍历排序后的集合，依次写入到新文件
            for (String ln: list){
                writer.write(ln); // 写入
                writer.newLine(); // 换行
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
