package com.process.NetworkComm;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServerUDP {
    public static void main(String[] args) throws Exception {
        // 创建服务端对象
        DatagramSocket s = new DatagramSocket(6666);
        // 创建一个数据包对象，用于接收数据的
        byte[] bytes = new byte[1024 * 64]; // 64kb
        DatagramPacket p = new DatagramPacket(bytes,bytes.length);
        // 开始正式使用数据包来接收客户端发来的数据【只接收一次】
        // s.receive(p);


        while (true){
            s.receive(p);
            // 获取数据包数据大小
            int len = p.getLength();
            String rs = new String(bytes,0,len);
            System.out.println("服务端接收数据-----" + rs);
            System.out.println(p.getPort()); // 客户端没有指定，则随机
            System.out.println(p.getAddress());
            System.out.println("-----------------------");
        }
        // s.close(); // 释放资源【一般服务端不会关闭】
    }
}
