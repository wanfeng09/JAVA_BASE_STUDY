package com.process.NetworkComm;

import java.net.InetAddress;
import java.net.UnknownHostException;

/*
* 基本通信架构
* CS架构（Client客户端/Server服务端）、BS架构（Browser浏览器/Server服务端）
* Client客户端：如微信、IDEA、qq
* Browser浏览器：谷歌、IE、火狐
* 无论CS架构，还是BS架构的软件都必须依赖网络编程
*
* 网络通信关键三要素【ip、端口、协议】
* IP：设备在网络中的地址，是唯一标识。【ipv4/ipv6】
* 端口：应用程序在设备中的唯一标识。
* 端口分类：周知端口（0-1023），注册端口（1024-49151），动态端口（49152 - 65535）
* 程序开发选用端口请使用注册端口区间范围，同一个设备不可出现两个程序端口号一样。
* 协议：连接和数据在网络中的传输的规则。
* 开放式网络互联标准：OSI网络参考模型
* OSI网络参考模型：全球网络互联标准
* TCP/IP网络模型：事实上的国际标准
*
* |OSI网络参考模型|TCP/IP网络模型|各层对应|面向操作|
* |应用层|应用层|HTTP、FTP、SMTP...|应用程序需要关注的：浏览器、邮箱。【程序开发在这一层】|
* |表示层|同上|同上|同上|
* |会话层|同上|同上|同上|
* |传输层|传输层|UDP、TCP...|选择使用的UDP、TCP协议|
* |网络层|网络层|IP...|封装源和目标IP|
* |数据链路层|数据链路 + 物理|比特流...|物理设备中传输|
* |物理层|同上|同上|同上|
*
* 传输层的2个通信协议
* UDP（User Datagram Protocol）: 用户数据报协议
* 优点：通信效率高! 语音通话、视频直播
* 特点：无连接、不可靠通信
* 不事先建立连接，数据按照包发，包数据包含：自己IP、程序端口、目的地IP、程序端口和数据（限制64KB内）等。
* 发送方不管对方是否在线，数据在中间丢失也不管，如果接收方收到数据也不返回确认，所以是不可靠通信。
*
* TCP（Transmission Control Protocol）：传输控制协议
* 优点：通信可靠! 网页、文件下载、支付
* 特点：面向连接、可靠通信
* 三次握手建立连接，传输数据进行确认，四次挥手断开连接
*
* 三次握手：简单来说确定双方收发消息没有问题，以保证数据传输的可靠性。
* 客户端发出连接请求【客户端-发出】，服务端接收到信息【服务端-接收】，返回一个响应【服务端-发出】，客户端接收到响应信息【客户端-接收】，
* 确保信息没有错误，再次发送确认信息，建立可靠连接
*
* 四次挥手：确保双方数据的收发都已经处理完成。
* 客户端发出断开连接请求，服务端返回一个响应（稍等：等待服务器将最后数据处理完毕），然后再返回一个响应（确认断开），客户端再次发送正式确认断开连接请求。
*
* 公网ip：连接互联网的ip地址
* 内网ip: 局域网，组织机构内部使用【常见192.168.0.0-192.168.255.255】
* 本机ip: 127.0.0.1/localhost
* ping ip地址：检查网络是否连通
*
* InetAddress
* 代表IP地址
* 方法
* getLocalHost() 获取本机ip，会以一个InetAddress的对象返回
* getByName(String host) 根据IP地址或者域名，返回一个InetAddress对象
* getHostName() 获取该IP地址对象对应的主机名
* getHostAddress() 获取该IP地址对象中的ip地址信息
* isReachable(int timeout) 在指定毫秒内，判断主机与该ip对应的主机是否能连通
*
* 注意：先启动服务端，在启动客户端
* UDP（User Datagram Protocol）: 用户数据报协议
* DatagramSocket：用于创建客户端、服务端
* 构造器
* DatagramSocket() 创建客户端的Socket对象，系统会随机分配一个端口号
* DatagramSocket(int port) 创建服务端的Socket对象，并指定端口号
* 方法
* send(DatagramPacket dp) 发送数据包
* receive(DatagramPacket p) 使用数据包接收数据
*
* DatagramPacket：创建数据包
* 构造器
* DatagramPacket(byte[] buf,int length,InetAddress address,int port) 创建发出去的数据包对象
* DatagramPacket(byte[] buf,int length) 创建用来接收数据的数据包
* 参数
* byte[] buf：封装要发出去的数据
* int length：发送出去的数据大小（字节个数）
* InetAddress address：服务器的IP地址
* int port：服务器的程序端口
*
* 方法
* getLength() 获取数据包，实际接收到的字节个数
*
* TCP（Transmission Control Protocol）：传输控制协议
* Socket：用于创建客户端
* 构造器
* Socket(String host,int port) 根据指定的服务器IP，端口号请求与服务器建立连接，连接通过，就获得了客户端socket
* 方法
* getOutputStream() 获得字节输出流对象
* getInputStream() 获得字节输入流对象
*
* ServerSocket：用于创建服务端
* 构造器
* ServerSocket(int port) 为服务端程序注册端口
* 方法
* accept() 阻塞等待客户端的连接请求，一旦与某个客户端成功连接，则返回服务端这边的Socket对象
*
* 使用TCP通信实现：多发多收消息
* 客户端使用死循环，让用户不断输入消息
* 服务端也是用死循环，控制服务端收完消息，继续等待接收下一个消息
*
* 多个客户端同时通信
* 通过多线程实现
*
* */
public class Demo {
    public static void main(String[] args) throws Exception {
        InetAddress ip = InetAddress.getLocalHost();
        System.out.println(ip);
        System.out.println(ip.getHostAddress());
        System.out.println(ip.getHostName());

        InetAddress ip2 = InetAddress.getByName("www.baidu.com");
        System.out.println(ip2.getHostAddress());
    }
}
