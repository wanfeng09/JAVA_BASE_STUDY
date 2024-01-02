package com.process.NetworkComm;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientTCP {
    public static void main(String[] args) throws Exception {
        // oneMul ();
        clientMul();

    }

    public static void oneMul() throws Exception {
        // 创建客户端
        Socket socket = new Socket("localhost",6666);
        // 使用Socket对象调用getOutputStream()方法得到字节输出流
        OutputStream os = socket.getOutputStream();
        // 把原始的字节输出流包装成数据输出流
        DataOutputStream dos = new DataOutputStream(os);
       /*
       // 使用字节输出流完成数据的发送
        dos.writeUTF("客户端发送消息");
        // 释放资源，关闭Socket管道
        dos.close();
        socket.close();
        */
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("请说：");
            String msg = sc.nextLine();
            if("exit".equals(msg)){
                System.out.println("退出成功");
                dos.close();
                socket.close();
                break;
            }
            dos.writeUTF(msg);
            dos.flush();
        }
    }

    public static void clientMul() throws IOException {
        // 创建客户端
        Socket socket = new Socket("localhost",6666);
        // 创建一个独立线程
        new Thread(new clientReaderThread(socket)).start();
        // 使用Socket对象调用getOutputStream()方法得到字节输出流
        OutputStream os = socket.getOutputStream();
        // 把原始的字节输出流包装成数据输出流
        DataOutputStream dos = new DataOutputStream(os);
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("请说：");
            String msg = sc.nextLine();
            if("exit".equals(msg)){
                System.out.println("退出成功");
                dos.close();
                socket.close();
                break;
            }
            dos.writeUTF(msg);
            dos.flush();
        }
    }

}
class clientReaderThread implements Runnable{
    private Socket socket;

    public clientReaderThread(Socket socket) {
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
                    System.out.println( "客户端线程： "+ rs);
                    System.out.println("------------");
                }catch (Exception e){
                    System.out.println(socket.getRemoteSocketAddress()+"--离线了！");
                    din.close();
                    socket.close();
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
