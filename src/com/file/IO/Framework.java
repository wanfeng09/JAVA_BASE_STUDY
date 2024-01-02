package com.file.IO;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/*
* 框架
* 解决某类问题，编写的一套类，接口等，大多框架都是第三方研发的
* 好处：在框架的基础上开发，可以得到优秀的软件架构，并能提高开发效率
* 形式：一般是把类、接口等编译成class形式，在压缩成一个.jar接口的文件发行出去。
*
* IO框架
* 封装了java提供的对文件、数据进行操作的代码，对外提供了更简单的方式来对文件进行操作，对数据进行读写等。
* commons-io
* 下载地址：https://commons.apache.org/proper/commons-io/download_io.cgi
* 选择Binaries下的.zip包
* 在项目下新建lib文件夹，解压.zip包，找到.jar文件复制到lib文件夹下
* 然后.jar右键选择Add as Library（添加为库）进行资源整合，点击ok
* 在类中导包使用
*
* apache开源基金组织提供的一组有关IO操作的小框架，目的是提高IO流开发效率
* FileUtils类提供的部分方法
* copyFile(File srcFile,File destFile) 复制文件
* copyDirectory(File srcDir,File destDir) 复制文件夹
* deleteDirectory(File directory) 删除文件夹
* readFileToString(File file,String encoding) 读数据
* writeStringToFile(File file,String data,String charName,boolean append) 写数据
*
* IOUtils类提供的部分方法
* copy(InputStream in,OutStream out) 复制文件
* copy(Reader r,Writer w) 复制文件
* write(String data,OutStream out,String charset) 指定字符集写数据
*
*
* */
public class Framework {
    public static void main(String[] args) throws Exception {
        // 文件复制
        // FileUtils.copyFile(new File("D:\\java\\demo\\IOtest\\a.txt"), new File("D:\\java\\demo\\IOtest\\b.txt"));
        // 目录复制
        // FileUtils.copyDirectory(new File("D:\\java\\demo\\IOtest"),new File("D:\\java\\demo\\IOtest-copy"));
        // 删除目录
        // FileUtils.deleteDirectory(new File("D:\\java\\demo\\IOtest-copy"));

        // java提供的原生代码
        // 复制，创建新文件
        // Files.copy(Path.of("D:\\java\\demo\\IOtest\\a.txt"),Path.of("D:\\java\\demo\\IOtest\\f.txt"));
        // 读取文件内容
        System.out.println(Files.readString((Path.of("D:\\java\\demo\\IOtest\\b.txt"))));
    }
}
