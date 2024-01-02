package com.process.NetworkComm;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
* BS架构
* 基本原理
* HTTP协议规定：响应给浏览器的数据格式必须满足如下格式
* ---------
* 协议版本号 状态码 状态符 回车换行【例子：HTTP/1.1 200 OK】
* 头部字段名：值 回车换行 【例子：Content-Type :text/html;charset=UTF-8】
* ......
* 响应正文【html】
* ---------
*
* 浏览器访问服务器，并立即让服务器返回网页内容
* 注意：服务器必须给浏览器响应HTTP协议规定的数据格式，否则浏览器不识别返回的数据。
*
* 访问地址：http://localhost:8080/
*
*
* */
public class BSDemo {
    public static void main(String[] args) throws Exception {
        // 浏览器访问服务器，并立即让服务器返回网页内容【端口必须8080】
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("启动成功");

        // 使用线程池优化
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2,6,0,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(6),Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());

        while (true){
            Socket socket = serverSocket.accept();
            System.out.println("上线了"+socket.getRemoteSocketAddress());
            pool.execute(new browserReaderThread(socket));
           // new Thread(new browserReaderThread(socket)).start();
        }
    }
}

class browserReaderThread implements Runnable{
    private Socket socket;

    public browserReaderThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            OutputStream os = socket.getOutputStream();
            PrintStream ps = new PrintStream(os);
            ps.println("HTTP/1.1 200 OK");
            ps.println("Content-Type:text/html;charset=UTF-8");
            ps.println();
            ps.println("hello world!");
            ps.close();
            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
