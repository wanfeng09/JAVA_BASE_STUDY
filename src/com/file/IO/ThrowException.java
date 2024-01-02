package com.file.IO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/*
*
* 如何解决抛出异常操作
* try-catch-finally
* 格式 try{}catch(IOException e){ e.printStackTrace() }finally{}
* 特点 finally无论代码是否异常都会执行，除非jvm终止
* 作用 用于程序执行完成后进行资源的释放操作
*
* try-with-resource
* 该资源使用完毕后，会自动调用其close()方法，完成对资源的释放
* 格式 try(定义资源1; 定义资源2...){}catch(异常类名 变量名){}
* 资源一般指 最终实现了AutoCloseable接口
*
*
* */
public class ThrowException {
    public static void main(String[] args) {
        // test();
        // test2();
        // System.out.println(test3()); // 111
        // test7();
        test6();
    }

    public static void test(){
        try {
            System.out.println(10/0);
        }catch (Exception e){
            e.printStackTrace(); // java.lang.ArithmeticException: / by zero
        }finally {
            System.out.println("代码异常");
        }
    }

    public static void test2(){
        try {
            System.out.println(10/2); // 5
            // return; // finally也会执行
            System.exit(0); // 虚拟机 finally不会执行
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("代码执行");
        }
    }

    public static int test3(){
        try {
            return 10 / 5;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }finally {
            // 不能再finally中return数据，否则返回数据不正常
            return 111;
        }
    }

    public static void test7(){
        InputStream in = null;
        OutputStream out = null;
        try {
            System.out.println(10/0);
            in = new FileInputStream("D:\\java\\demo\\IOtest\\a.txt");
            out = new FileOutputStream("D:\\java\\demo\\IOtest\\b.txt");

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 关闭流【臃肿，不优雅，推荐使用try-with-resource】
            // out.close(); 报错 是否提前关过流，以及流还未创建
            try {
                if (out != null) out.close();
            }catch (Exception e){
                e.printStackTrace();
            }

            try {
                if (in != null) in.close();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    public static void test6(){
        try(
                // 注意：只能放置资源对象 , 资源: 实现了AutoCloseable接口
                InputStream in = new FileInputStream("D:\\java\\demo\\IOtest\\a.txt");
                OutputStream out = new FileOutputStream("D:\\java\\demo\\IOtest\\b.txt");
                ResourceDemo r = new ResourceDemo()
                ) {
            // System.out.println(10/0);
            System.out.println(r);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

// 自定义资源对象
class ResourceDemo implements AutoCloseable {

    @Override
    public void close() throws Exception {
        System.out.println("释放资源");
    }
}
