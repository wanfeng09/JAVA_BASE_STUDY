package com.file.IO;

import java.io.*;

/*
* 原始流、缓冲流的性能分析
* 测试用例：分别使用原始的字节流，以及字节缓冲流复制一个很大的视频
* 测试步骤
* 使用低级的字节流按照一个一个字节的形式复制文件【速度超级无敌慢】
* 使用低级的字节流按照字节数组的形式复制文件【速度较慢】
* 使用高级的缓冲字节流按照一个一个字节的形式复制文件【速度较慢】
* 使用高级的缓冲字节流按照字节数组的形式复制文件【速度最快】
*
* 通过字节数组形式复制【字节越大，速度越大，也并不是越大越快，有最高限制】
*
*
* */
public class FlowCompare {
    // 复制视频的路径
    private static final String SRC_FILE = "D:\\java\\demo\\IOtest\\video\\1369823867-1-192.mp4";
    // 复制到那个目录下
    private static final String DEST_FILE = "D:\\java\\demo\\IOtest\\video_copy\\";
    public static void main(String[] args) {
        // 使用低级的字节流按照一个一个字节的形式复制文件
        // originOneCopy(); // 超级慢，不用看了
        // 使用低级的字节流按照字节数组的形式复制文件
        originByteCopy(); // 0.048s
        // 使用高级的缓冲字节流按照一个一个字节的形式复制文件
        // BufferOneCopy(); // 0.289s
        // 使用高级的缓冲字节流按照字节数组的形式复制文件
        // 速度最快
        BufferByteCopy(); // 0.014s
    }
    public static void originOneCopy(){
        long startTime = System.currentTimeMillis(); // 记录开始时间
        try (
                InputStream in = new FileInputStream(SRC_FILE);
                OutputStream out = new FileOutputStream(DEST_FILE + "copyVideoName.mp4");
                ){

            int num;
            while ((num = in.read()) != -1){
                out.write(num);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis(); // 记录结束时间
        System.out.println("原始字节流一个一个字节复制耗时" + (endTime - startTime) / 1000.0 + "s");
    }

    public static void originByteCopy(){
        long startTime = System.currentTimeMillis(); // 记录开始时间
        try (
                InputStream in = new FileInputStream(SRC_FILE);
                OutputStream out = new FileOutputStream(DEST_FILE + "copyVideoName2.mp4");
        ){

            byte[] bytes = new byte[1024 * 64]; // 1kb : 0.048s --- 8kb : 0.016s --- 64kb: 0.0s
            int len;
            while ((len = in.read(bytes)) != -1){
                out.write(bytes,0,len);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis(); // 记录结束时间
        System.out.println("原始字节流按照字节数组复制耗时" + (endTime - startTime) / 1000.0 + "s");
    }

    public static void BufferOneCopy(){
        long startTime = System.currentTimeMillis(); // 记录开始时间
        try (
                InputStream in = new FileInputStream(SRC_FILE);
                BufferedInputStream bin = new BufferedInputStream(in);
                OutputStream out = new FileOutputStream(DEST_FILE + "copyVideoName3.mp4");
                BufferedOutputStream bout = new BufferedOutputStream(out);
        ){

            int num;
            while ((num = bin.read()) != -1){
                bout.write(num);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis(); // 记录结束时间
        System.out.println("缓冲字节流一个一个字节复制耗时" + (endTime - startTime) / 1000.0 + "s");
    }

    public static void BufferByteCopy(){
        long startTime = System.currentTimeMillis(); // 记录开始时间
        try (
                InputStream in = new FileInputStream(SRC_FILE);
                BufferedInputStream bin = new BufferedInputStream(in);
                OutputStream out = new FileOutputStream(DEST_FILE + "copyVideoName6.mp4");
                BufferedOutputStream bout = new BufferedOutputStream(out);
        ){

            byte[] bytes = new byte[1024 * 8]; // 1kb: 0.016s 8kb: 0.015s 64kb: 0.001s
            int len;
            while ((len = bin.read(bytes)) != -1){
                bout.write(bytes,0,len);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis(); // 记录结束时间
        System.out.println("缓冲字节流按照字节数组复制耗时" + (endTime - startTime) / 1000.0 + "s");
    }
}
