package com.file.FileBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

/*
* File --- java.io包下的类 【文件、文件夹】
* File类只能对文件本身进行操作，不能读写文件里面存储的数据
* 文件存储在计算机硬盘中，数据不会丢失。
*
* 创建文件对象
* File(String path) 根据文件路径创建文件对象
* File(String parent,String child) 根据父路径和子路径名字创建文件对象
* File(File parent,String child) 根据父路径对应的文件对象和子路径名字创建文件对象
*
* 获取文件信息
* length() 获取文件的大小，返回字节个数
* exists() 判断当前文件对象，对应的文件路径是否存在，存在返回true
* isFile() 判断当前文件对象指代的是否是文件，是文件返回true
* isDirectory() 判断当前文件对象指代的是否是文件夹，是文件夹返回true
* getName() 获取文件的名称（包含后缀）
* lastModified() 获取文件的最后修改时间
* getPath() 获取创建文件对象时，使用的路径
* getAbsolutePath() 获取绝对路径
*
* 创建文件、文件夹
* createNewFile() 创建一个新的空的文件
* mkdir() 只能创建一级文件夹
* mkdirs() 可以创建多级文件夹
*
* 删除文件、文件夹
* delete() 删除文件、空文件夹【删除后的文件不会进入回收站】
*
* 判断文件类型
*
* 绝对路径：从盘符开始 new File("D:\\...")
* 相对路径：当前工程下（java_base_study）的目录。new File("src\\...")
*
* 遍历文件夹方法
* list() 获取当前目录下的所有一级文件名称到一个字符串数组中去返回
* listFiles() 获取当前目录下所有一级文件对象到一个文件对象数组中去返回（重点）
* listFiles()注意事项
* 当主调是文件，或者路径不存在时，返回null
* 当主调是空文件夹时，返回一个长度为0的数组
* 当主调是一个有内容的文件夹时，将里面所有一级文件和文件夹的路径放在File数组中返回
* 当主调是一个文件夹，且里面有隐藏文件时，将里面所有文件和文件夹里的路径放在File数组中返回，包含隐藏文件
* 当主调是一个文件夹，但是没有权限访问该文件夹时，返回null
*
* */
public class Demo {
    public static void main(String[] args) throws IOException {
        // 创建File对象，指向某个具体的文件路径，也可指向不存在的文件路径
        // 3种写法
        // File f1 = new File("D:\\java\\demo");
        // File f1 = new File("D:/java/demo");
        File f1 = new File("D:" + File.separator + "java" + File.separator + "demo");
        System.out.println(f1.length()); // 4096 文件大小
        File f2 = new File("D:\\java\\demo\\fileTest\\no11.txt");
        // 判断文件是否存在
        System.out.println(f2.exists()); // false
        System.out.println(f1.exists()); // true
        System.out.println("-------------------------");

        // File f3 = new File("D:\\java\\demo\\java_base_study\\src\\com\\file\\FileBase");
        // 相对路径
        File f3 = new File("src\\com\\file\\FileBase");
        System.out.println(f3.getName()); // FileBase
        System.out.println(f3.exists()); // true
        System.out.println(f3.isFile()); // false
        System.out.println(f3.isDirectory()); // true
        // System.out.println(f3.lastModified()); // 1702263035193
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf.format(f3.lastModified())); // 2023-12-11
        System.out.println(f3.getPath()); // src\com\file\FileBase
        System.out.println(f3.getAbsolutePath()); // D:\java\demo\java_base_study\src\com\file\FileBase

        // 创建文件
        File f6 = new File("D:\\java\\demo\\fileTest");
        System.out.println(f6.createNewFile()); // false 【已经存在】 抛出异常 IOException

        File f7 = new File("D:\\java\\demo\\fileTest\\a.txt");
        System.out.println(f7.createNewFile()); // true 【创建文件成功】

        File f8 = new File("D:\\java\\demo\\fileTest\\aaa");
        System.out.println(f8.mkdir()); // true 【创建一级文件夹成功】

        File f9 = new File("D:\\java\\demo\\fileTest\\aaa\\ff\\gg\\cc");
        System.out.println(f9.mkdirs()); // true 【创建多级文件夹成功】

        // 删除文件
        System.out.println(f9.delete()); // 删除了cc  【只能删除空的文件夹】


        System.out.println("------------------------------------------------");
        System.out.println("------------------------------------------------");
        // 遍历文件
        File f10 = new File("D:\\java\\demo\\fileTest");
        String[] fileNames = f10.list();
        for (String name : fileNames){
            System.out.println(name);
        }

        File[] files = f10.listFiles();
        for (File file: files){
            System.out.println(file.getAbsolutePath());
        }
    }
}
