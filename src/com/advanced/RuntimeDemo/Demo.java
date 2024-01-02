package com.advanced.RuntimeDemo;

import java.io.IOException;

/*
* 代表程序所在的运行环境
* Runtime是一个单例类
* 常用方法
* getRuntime() 返回与当前Java应用程序关联的运行时的对象
* exit()终止当前运行的虚拟机
* availableProcessors()返回Java虚拟机可用的处理器数
* totalMemory()返回Java虚拟机中的内存总量
* exec()启动某个程序，并返回代表该程序的对象[Process p]
* Thread.sleep(5000) 让程序在这里暂停5s后继续往下走【抛出异常】
* p.destroy() 销毁/关闭程序
* */
public class Demo {
    public static void main(String[] args) throws IOException, InterruptedException {
        Runtime r = Runtime.getRuntime();
//        r.exit(0);
        int num =  r.availableProcessors();
        System.out.println(num);
        System.out.println(r.totalMemory()/1024.0/1024.0 + "MB"); // 1024 = 1k 1024*1024=1M
        System.out.println(r.freeMemory()/1024.0/1024.0 + "MB");
        Process p = r.exec("\"D:\\Program Files\\Tencent\\QQ\\Bin\\QQScLauncher.exe\"");
        Thread.sleep(3000);
        p.destroy(); // 没有关闭成功
    }
}
