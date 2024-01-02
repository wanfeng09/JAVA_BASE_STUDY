package com.process.NetworkComm;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClientUDP {
    public static void main(String[] args) throws Exception {
        // 创建客户端对象
        DatagramSocket c = new DatagramSocket();

        // byte[] bytes = "我是客户端数据".getBytes();

        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("请输入");
            String msg = scanner.nextLine();
            if ("exit".equals(msg)){
                System.out.println("程序已关闭");
                c.close();
                break;
            }
            byte[] bytes = msg.getBytes();
            // 创建发出去数据包
            DatagramPacket p = new DatagramPacket(bytes,bytes.length, InetAddress.getLocalHost(),6666);
            // 发送数据包
            c.send(p);
        }
        // System.out.println("数据发送完毕");
        // 释放资源
        // c.close();
    }
}
