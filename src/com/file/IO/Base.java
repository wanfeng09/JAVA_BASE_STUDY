package com.file.IO;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;

/*
* IO流
* IO流的体系
* 字节流
* 字节输入流【抽象类：InputStream，实现类 --- 原始流/低级流：FileInputStream，包装流/处理流：BufferedInputStream（字节缓冲输入流），DataInputStream（数据输入流），ObjectInputStream（对象字节输入流）】
* 字节输出流【抽象类：OutputStream，实现类 --- 原始流/低级流：FileOutputStream，包装流/处理流：BufferedOutputStream（字节缓冲输出流），DataOutputStream（数据输出流），ObjectOutputStream（对象字节输出流）】
* PrintStream（打印流）
*
* 字符流
* 对原始流进行包装，已提高原始流读写数据的性能
* 字符输入流【抽象类：Reader，实现类 --- 原始流/低级流：FileReader，包装流/处理流：BufferedReader（字符缓冲输入流），InputStreamReader（字符输入转换流）】
* 字符输出流【抽象类：Writer，实现类 --- 原始流/低级流：FileWriter，包装流/处理流：BufferedWriter（字符缓冲输出流），OutputStreamWriter（字符输出转换流）】
* PrintWriter（打印流）
*
*
*
* 按流方向
* I: input 输入流，负责把数据读到内存中去
* O: output 输出流，负责写数据出去
*
* 按流中数据的最小单位
* 字节流：适合操作所有类型的文件（音频、视频、图片、文本文件复制，转移等）
* 字符流：只适合操作纯文本文件（读写txt、java文件等）
*
* 字节（字符）输入流：以内存为基准，来自磁盘文件、网络中的数据以字节（字符）的形式读入到内存中去的流
* 字节（字符）输出流：以内存为基准，把内存中的数据以字节（字符）写出到磁盘文件或者网络中去的流
*
* FileInputStream(文件字节输入流)
* 作用：以内存为基准，可以把磁盘文件中的数据以字节的形式读入到内存中去
* 构造器
* FileInputStream(File file) / FileInputStream(String pathName)  创建字节输入流管道与源文件接通
* 方法
* read() 每次读取一个字节返回，如果发现没有数据可读返回-1 【读取汉字输出会出现乱码，读取性能差!】
* read(byte[] buffer) 每次用一个字节数组去读取数据，返回字节数据读取了多少个字节。发现没有可读返回-1 【读取汉字输出会出现乱码，性能得到了提升】
*
* 一次性读取完全部字节
* 定义一个字节数组与被读取的文件大小一样大，然后使用改字节数组，一次读完文件的全部字节
* 使用byte[] readAllBytes() 直接将当前字节输入流对应的文件对象的字节数据装到一个字节数组返回
*
* 一次性读取完全部字节可以避免乱码吗？如果文件过大，创建的字节数组也会过大，可能引起内存溢出
*
* FileOutputStream(文件字节输出流)
* 作用：以内存为基准，把内存中的数据以字节的形式写到文件中去
* 构造器
* FileOutputStream(File file) 、FileOutputStream(String path) 创建字节输出流管道与文件对象接通【覆盖】
* FileOutputStream(File file,boolean) 、FileOutputStream(String path,boolean) 追加数据
* 方法
* write(int a) 写一个字节出去
* write(byte[] buffer) 写一个字节数组出去
* write(byte[] buffer,int pos,int len) 写一个字节数组的一部分出去
*
* FileReader(文件字符输入流)
* 作用：以内存为基准，可以把磁盘文件中的数据以字符的形式读入到内存中去
* 构造器
* FileReader(File file) / FileReader(String pathName) 创建字符输入流管道与源文件接通
* 方法
* read() 每次读取一个字符返回，如果发现没有数据可读返回-1 【读取汉字不会出现乱码，读取性能较差!】
* read(char[] buffer) 每次用一个字符数组去读取数据，返回字符数据读取了多少个字符。发现没有可读返回-1 【读取汉字不会出现乱码，性能得到了提升】
*
* FileWriter(文件字符输出流)
* 作用：以内存为基准，把内存中的数据以字符的形式写到文件中去
* 构造器
* FileWriter(File file) 、FileWriter(String path) 创建字符输出流管道与文件对象接通【覆盖】
* FileWriter(File file,boolean) 、FileWriter(String path,boolean) 追加数据
* 方法
* write(int c) 写一个字符
* write(String str) 写一个字符串
* write(String str,int off,int len) 写一个字符串的一部分
* write(char[] buffer) 写一个字符数组
* write(char[] buffer,int pos,int len) 写一个字符数组的一部分
*
* 注意：字符输出流写出数据后，必须刷新流flush()，或者关闭流（包含刷新操作），写出去的数据才能生效
*
* BufferedInputStream(文件字节缓冲输入流) BufferedOutputStream(文件字节缓冲输出流)
* 作用：字节缓冲输入流（输出流）自带了8kb缓冲池，提高字节流读写数据的性能
* 构造器（把原始流的字节输入流（输出流）包装成一个高级的缓冲字节输入流（输出流），从而提高读数据的性能）
* BufferedInputStream(InputStream in)
* BufferedOutputStream(OutputStream out)
*
* BufferedReader(文件字符缓冲输入流) BufferedWriter(文件字符缓冲输出流)
* 作用：字符缓冲输入流（输出流）自带了8kb缓冲池，提高字符流读取字符数据的性能
* 构造器（把原始流的字符输入流（输出流）包装成一个高级的缓冲字符输入流（输出流），从而提高读数据的性能）
* BufferedReader(Reader r)
* BufferedWriter(Writer w)
* 字符缓冲流新增功能：按照行读取字符
* 输入流：readLine() 读取一行数据返回。没有可读数据，返回null
* 输出流：newLine() 换行
*
* 如果代码编码和被读取的文本文件的编码是一致的，使用字符流读取文本文件时不会出现乱码！
* 如果代码编码和读取的文本文件的编码是不一致的，使用字符流读取文本文件时就会出现乱码！
*
* InputStreamReader(字符输入转换流)：解决不同编码时，字符流读取文本内容乱码的问题
* 先获取文件的原始字节流，再将棋按真实的字符集编码转成字符输入流。
* 构造器
* InputStreamReader(InputStream in) 把原始字节输入流，按照默认编码转换字符输入流(与直接用FileReader效果一样)
* InputStreamReader(InputStream in,String charset) 把原始字节输入流，指定字符集编码转换字符输入流（解决编码不一致乱码问题）
*
* 指定字符集编码输入流
* 1、通过String提供的getBytes()方法解决
* 2、使用OutputStreamWriter(字符输出转换流)
*
* OutputStreamWriter(字符输出转换流)
* 控制写出去的字符使用什么字符集编码
* 获取字节输出流，指定字符集编码将其转换成字符输出流。
* OutputStreamWriter(OutputStream out) 把原始的字节输出流按照代码默认编码转换成字符输出流
* OutputStreamWriter(OutputStream out,String charset) 把原始的字节输出流指定编码转换成字符输出流（指定字符集编码）
*
*
* DataInputStream(数据输入流)
* 作用：用于读取数据输出流写出去的数据
* 构造器
* DataInputStream(InputStream in) 创建新数据输入流包装基础的字节输入流
* 方法
* readByte() 读取字节类型数据返回
* readInt() 读取int类型数据返回
* readDouble() 读取double类型数据返回
* readUTF() 读取字符串（UTF-8）数据返回
* readInt()/read(byte[]) 支持读字节数据进来
*
*
* DataOutputStream(数据输出流)【乱码是类型+数据包装一起写入文件了，读取数据DataInputStream要一一对应写入顺序】
* 作用：允许把数据和其类型一并写出去
* 构造器
* DataOutputStream(OutputStream out) 创建新数据输出流包装基础的字节输出流
* 方法
* writeByte(int v) 把byte类型的数据写入基础的字节输出流
* writeInt(int v) 把int类型的数据写入基础的字节输出流
* writeDouble(Double v) 把double类型的数据写入基础的字节输出流
* writeUTF(String str) 把字符串数据以UTF-8编码成字节写入基础的字节输出流
* write(int/byte[]一部分) 支持写字节数据出去
*
*
* ObjectInputStream（对象字节输入流） 反序列化：读取
* 作用：可以把Java对象进行反序列化，把存储在文件中的Java对象读入到内存中来
* 构造器
* ObjectInputStream(InputStream in) 创建对象字节输入流，包装基础的字节输入流
* 方法
* readObject() 把存储在文件中的Java对象读出来
*
* ObjectOutputStream（对象字节输出流） 序列化:写入
* 作用：可以把Java对象进行序列化，把Java对象存入到文件中去
* 构造器
* ObjectOutputStream(OutputStream out) 创建对象字节输入流，包装基础的字节输出流
* 方法
* writeObject(Object o) 把对象写出去
*
* 注意：
* 通过transient让成员变量不参与序列化
* 对象如果参与序列化，必须实现序列化接口（java.io.Serializable）
* 如何一次序列化多个对象：ArrayList集合可以存储多个对象，然后直接对集合进行序列化即可（ArrayList集合已经实现了序列化接口）
*
* PrintStream/PrintWriter(打印流)
* 作用：更方便、更高效的将数据打印出去
* 区别：打印数据的功能上是一模一样的，都是使用方便，性能高效（核心优势）
* PrintStream继承自字节输出流OutputStream，因此支持写字节数据的方法
* PrintWriter继承字符输出流Writer，因此支持写字符数据出去
*
* 构造器
* PrintStream/c(OutputStream/File/String) 打印流直接通向字节输出流、文件、文件路径
* PrintStream/PrintWriter(String fileName,Charset charset) 可以指定写出去的字符编码
* PrintStream/PrintWriter(OutputStream out,boolean autoFlush) 可以指定实现自动刷新
* PrintStream/PrintWriter(OutputStream out,boolean autoFlush,String encoding) 可以指定实现自动刷新，并且指定字符编码
* 方法
* println() 打印任意类型数据出去
* write(int/byte[]/..) 支持写字节数据出去 【PrintStream】
* write(int/String/char[]/..) 支持写字符数据出去【PrintWriter】
*
* 注意：
* 高级流不支持追加数据，要转换原始流
* 打印流：输出语句的重定向【改变系统打印位置System.setOut(PrintStream p)】
*
*
* */
public class Base {
    public static void main(String[] args) throws Exception {
        // 字节输入、输出流
        // input();
        // output();
        // copyImg();

        // 字符输入、输出流
        // reader();
        // write();

        // 字节缓冲流
        // bufferedByte();

        // 字符缓冲流
        // bufferedChar();

        // 指定字符集编码（InputStreamReader，OutputStreamWriter）
        // differentCode();

        // 数据输入（输出）流
        // dataStream();

        // （反）序列化流
        // objectStream();

        // PrintStream
        // printTest1();
        // PrintStream
        // printTest2();
        System.out.println("测试数据1");
        System.out.println("测试数据2");
        try(
                PrintStream p = new PrintStream("D:\\java\\demo\\IOtest\\e.txt")
                ){
            // 改变系统打印位置
            System.setOut(p);
            // 以下数据不会再控制台打印
            System.out.println("测试数据3");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void input() throws Exception {
        // 创建文件字节输入流管道【不会自己创建文件，需要指向已存在文件，进行文件读取】
        // InputStream in = new FileInputStream(new File("D:\\java\\demo\\IOtest\\a.txt")); // 报错原因怕你写错路径
        // 简化写法
        InputStream in = new FileInputStream("D:\\java\\demo\\IOtest\\a.txt");
        // 读取文件
        // int b1 = in.read();
        // System.out.println((char) b1); // a
        // System.out.println(in.read()); // -1
        // 读取汉字输出会出现乱码，读取性能差!
        // 一个字节一个字节的读取
        /*int b2;
        while ((b2 = in.read()) != -1){
            System.out.println((char) b2);
        }*/

        // 读取多个字节
        // byte[] bytes = new byte[3];
        // int len = in.read(bytes); // 每次读取了几个字节
        // System.out.println(len); // 3 / 2 / 1 / -1
        // String str = new String(bytes);
        // 注意：读取多少，就输出多少
        // String(byte[] bytes, int offset, int length)
        // 通过使用平台的默认字符集对指定的字节子数组进行解码来构造新的 String 字符数组。
        // String str = new String(bytes,0,len);
        // System.out.println(str); // 也会出现汉字乱码


        // 读取汉字输出会出现乱码，性能得到了提升
        /*
            byte[] bytes = new byte[3];
            int len;
            while ((len = in.read(bytes)) != -1){
                String str = new String(bytes,0,len);
                System.out.println(str);
            }
        */

        //  一次性读取完全部字节
       /* File file = new File("D:\\java\\demo\\IOtest\\a.txt");
        long size = file.length();
        System.out.println(size); // 获取文件字节数 4
        byte[] bytes = new byte[(int) size];

        int len = in.read(bytes); // 一次性读取完全部字节
        System.out.println(new String(bytes));*/

        byte[] bytes =  in.readAllBytes(); // 一次性读取完全部字节，返回字节数组
        System.out.println(Arrays.toString(bytes)); // [97, -26, -120, -111]
        System.out.println(new String(bytes));
        // 流使用完毕之后，必须关闭！释放性能！
        in.close();
    }

    public static void output() throws Exception {
        // 创建文件字节输出流管道【会自己创建一个空的文件】
        OutputStream out = new FileOutputStream("D:\\java\\demo\\IOtest\\b.txt");
        out.write(97);
        out.write('a');
        // out.write('我'); // 乱码【默认只能写出去一个字节】

        byte[] bytes = "你好，世界！H-----".getBytes();
        out.write(bytes);
        out.write("\r\n".getBytes()); // 换行符
        out.write(bytes,0,6); // 你好
        out.close();
    }
    // 字节流复制图片操作
    public static void copyImg() throws Exception {
        // 读取
        InputStream in = new FileInputStream("C:\\Users\\hui\\Desktop\\testImg\\test.jpeg");
        // 写入
        OutputStream out = new FileOutputStream("C:\\Users\\hui\\Desktop\\testImg\\copy\\test.jpeg");

        byte[] bytes = in.readAllBytes();
        // System.out.println(Arrays.toString(bytes));
        out.write(bytes);
        out.close();
        in.close();
        System.out.println("复制完成");

    }

    public static void reader(){
        try (
                Reader reader = new FileReader("D:\\java\\demo\\IOtest\\a.txt")

                ) {
            /*int c;
            while ((c = reader.read()) != -1){
                System.out.println((char) c); // 一个字符一个字符的读取，不会出现汉字乱码
            }*/
            char[] chars = new char[3];
            int len;
            while ((len = reader.read(chars)) != -1){
                System.out.println(len); // 3/2/1/-1 每次读取到的字符个数
                System.out.println(new String(chars,0,len));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void write(){
        try (
                // 覆盖
               // Writer writer = new FileWriter("D:\\java\\demo\\IOtest\\a.txt");
               // 数据追加
               Writer writer = new FileWriter("D:\\java\\demo\\IOtest\\a.txt",true);
        ) {
           writer.write('a');
           writer.write(97);
           writer.write('我');
           writer.write("\r\n");
           writer.write("你好，世界！-----------");
            writer.write("\r\n");
           writer.write("你好，世界！-----------",0,6); // 你好，世界！
            char[] chars = {'a','b','c'};
            writer.write(chars);
            writer.write(chars,0,2);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void bufferedByte(){
        try (
                // 字节缓冲输入流
                InputStream in = new FileInputStream("D:\\java\\demo\\IOtest\\a.txt");
                InputStream bin = new BufferedInputStream(in);
                // 字节缓冲输入流
                OutputStream out = new FileOutputStream("D:\\java\\demo\\IOtest\\b.txt");
                OutputStream bout = new BufferedOutputStream(out);
                ){
                byte[] bytes = new byte[1024];
                int len = bin.read(bytes);
                System.out.println(len); // 14 读取到的字节长度
                System.out.println(new String(bytes)); // 原始数据1

                // 数据写入
                bout.write(bytes,0,len);
                System.out.println("复制完成，覆盖");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void bufferedChar(){
        try (
                Reader reader = new FileReader("D:\\java\\demo\\IOtest\\a.txt");
                BufferedReader breader = new BufferedReader(reader);
                Writer writer = new FileWriter("D:\\java\\demo\\IOtest\\b.txt");
                BufferedWriter bwriter = new BufferedWriter(writer);
                ){
            // char[] chars = new char[10];
            // int len = breader.read(chars);
            // System.out.println(len); // 7
            // System.out.println(new String(chars)); // 原始数据1
            String line = breader.readLine();
            System.out.println(line); // 原始数据1 记录读取一行数据
            String line2 = breader.readLine();
            System.out.println(line2); // null

            // 写入数据
            bwriter.write('a');
            bwriter.write(97);
            bwriter.write('中');
            bwriter.newLine(); // 换行
            bwriter.write(line);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void differentCode(){
        try (
                // 文件编码GB2312
                // 代码编译UTF-8 编码不一致出现乱码
                // Reader r = new FileReader("D:\\java\\demo\\IOtest\\c.txt"); // 乱码
                // BufferedReader br = new BufferedReader(r);

                // 解决
                InputStream in = new FileInputStream("D:\\java\\demo\\IOtest\\c.txt");
                Reader r = new InputStreamReader(in,"GB2312"); // 字符输入转换流
                BufferedReader br = new BufferedReader(r);

                // 指定字符集编码写入文件
                OutputStream out = new FileOutputStream("D:\\java\\demo\\IOtest\\d.txt");
                Writer w = new OutputStreamWriter(out,"GBK");
                BufferedWriter bw = new BufferedWriter(w);

        ){
            String line;
            while ((line = br.readLine()) != null){
                System.out.println(line);
            }
            System.out.println("读取完成");

            bw.write("测试指定字符集编码GBK");
            bw.newLine();
            bw.write("测试数据");
            System.out.println("写入完成");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void dataStream(){
        try(
                // 数据输出流
                DataOutputStream out = new DataOutputStream(new FileOutputStream("D:\\java\\demo\\IOtest\\e.txt"));
                // 数据输入流
                DataInputStream in = new DataInputStream(new FileInputStream("D:\\java\\demo\\IOtest\\e.txt"));
                ){
            out.writeInt(97);
            out.writeDouble(99.88);
            out.writeUTF("字符串输入");
            out.writeBoolean(false);

            // 读取数据【读写一一对应】
            System.out.println(in.readInt()); // 97
            System.out.println(in.readDouble()); // 99.88
            System.out.println(in.readUTF()); // 字符串输入
            System.out.println(in.readBoolean()); // false

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void objectStream(){
        try (
                // 序列化
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("D:\\java\\demo\\IOtest\\e.txt"));
                // 反序列化
                ObjectInputStream in = new ObjectInputStream(new FileInputStream("D:\\java\\demo\\IOtest\\e.txt"));

                ){
                // 创建对象
                User u = new User("luxi",18,"123456");
                out.writeObject(u);
                System.out.println("序列化成功");

                // 读取数据
                System.out.println(in.readObject());


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void printTest1(){
        try (
                // 创建一个打印流管道
                // PrintStream p = new PrintStream("D:\java\demo\IOtest\e.txt", Charset.forName("GBK"));
                PrintStream p = new PrintStream("D:\\java\\demo\\IOtest\\e.txt");
                ){
                p.println(97);
                p.println("字符串数据");
                p.println(true);
            System.out.println("打印完成");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void printTest2(){
        try (
                // PrintWriter p = new PrintWriter("D:\\java\\demo\\IOtest\\e.txt");

                // 高级流不支持追加数据，要转换原始流
                // PrintWriter p = new PrintWriter("D:\\java\\demo\\IOtest\\e.txt",true); // 报错
                PrintWriter p = new PrintWriter(new FileOutputStream("D:\\java\\demo\\IOtest\\e.txt",true))
        ){
            p.println(97);
            p.println("字符串数据");
            p.println(true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

// 对象如果需要序列化，必须实现序列化接口
class User implements Serializable{
    private String name;
    private int age;
    // 通过transient让成员变量不参与序列化
    private transient String password;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                '}';
    }

    public User() {
    }

    public User(String name, int age, String password) {
        this.name = name;
        this.age = age;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
