package com.process.NetworkComm;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerTCP {
    // 存储在线socket集合
    public static List<Socket> onLineSockets = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        // oneMed();
        // mulMed();
        groupChat();
    }
    /*
    * 客户端服务端一对一
    * */
    public static void oneMed() throws Exception {
        // 创建服务端对象
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("---------服务端启动--------"+serverSocket);
        // 调用服务端对象的accept()方法，等待客户端的连接，并得到Socket管道对象
        Socket socket = serverSocket.accept();
        // 通过Socket对象调用getInputStream()方法得到字节输入流、完成数据的接收
        InputStream in = socket.getInputStream();
        // 把原始的字节输入流包装成数据输入流
        DataInputStream din = new DataInputStream(in);
       /*
        String rs = din.readUTF();
        System.out.println( "服务端接收消息： "+ rs);
         // 获取客户端IP地址
        System.out.println(socket.getRemoteSocketAddress());
        // 释放资源，关闭Socket管道
        din.close();
        socket.close();
        */
        // 多发多收消息【一对一】
        while (true){
            try {
                String rs = din.readUTF();
                System.out.println( "服务端接收消息： "+ rs);
                System.out.println("------------");
            }catch (Exception e){
                System.out.println(socket.getRemoteSocketAddress()+"离线了！");
                din.close();
                socket.close();
                break;
            }
        }
    }


    /*
     * 客户端多个，服务端一个，服务端接收所有客户端信息
     * */
    public static void mulMed() throws Exception{
        // 创建服务端对象
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("---------服务端启动--------"+serverSocket);
        while (true){
            Socket socket = serverSocket.accept();
            System.out.println("有人上线了：" + socket.getRemoteSocketAddress());
            // 启动线程
            new Thread(new ServerReaderThread(socket)).start();
        }
    }


    /*
     * 客户端多个，服务端一个，所有客户端进行通信【即时群聊】
     * 存储在线socket集合
     * */
    public static void groupChat() throws Exception {
        // 创建服务端对象
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("---------服务端启动--------"+serverSocket);
        while (true){
            Socket socket = serverSocket.accept();
            // 集合存储
            onLineSockets.add(socket);
            System.out.println("有人上线了：" + socket.getRemoteSocketAddress());
            // 启动线程
            new Thread(new ServerReaderThread(socket)).start();
        }
    }
}

class ServerReaderThread implements Runnable{
    private Socket socket;

    public ServerReaderThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream in = socket.getInputStream();
            DataInputStream din = new DataInputStream(in);
            while (true){
                try {
                    String rs = din.readUTF();
                    System.out.println( "服务端接收消息： "+ rs);
                    sendMsgToAll(rs);
                    System.out.println("------------");
                }catch (Exception e){
                    System.out.println(socket.getRemoteSocketAddress()+"离线了！");
                    din.close();
                    socket.close();
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 客户端共享消息方法
     * @param msg 转发消息
     * @throws Exception
     *
     */
    public void sendMsgToAll(String msg) throws Exception {
        for (Socket s: ServerTCP.onLineSockets){
            OutputStream os = s.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeUTF(msg);
            dos.flush();
        }
    }
}

