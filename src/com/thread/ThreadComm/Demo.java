package com.thread.ThreadComm;

import java.util.ArrayList;
import java.util.List;

/*
* 线程通信
* 前提：保证线程安全！
* 当多个线程共同操作共享的资源时，线程间通过某种方式互相告知自己的状态，以相互协调，并避免无效的资源争夺。
* 常见模型（生产者与消费者模型）-------------  生产-消费-生产-消费-生产-消费...
* 生产者线程负责生成数据
* 消费者线程负责消费生产者生产的数据
* 注意：生产者生产完数据应该等待自己，通知消费者消费，消费者消费完数据也应该等待自己，再通知生产者生产
*
* Object类等待和唤醒方法
* wait() 让当前线程等待并释放所占锁，直到另一个线程调用notify()/notifyAll()方法
* notify() 唤醒正在等待的单个线程
* notifyAll() 唤醒正在等待的所有线程
*
* 注意：上述方法应该使用当前锁对象进行调用
*
*
* */
public class Demo {
    public static void main(String[] args) {
        //
        Desk desk = new Desk();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    desk.put();
                }
            }
        },"生产线程1").start();
        new Thread(() -> {
            while (true){
                desk.put();
            }
        },"生产线程2").start();
        new Thread(() -> {
            while (true){
                desk.put();
            }
        },"生产线程3").start();
        new Thread(() -> {
            while (true){
                desk.get();
            }
        },"消费线程1").start();
        new Thread(() -> {
            while (true){
                desk.get();
            }
        },"消费线程2").start();

    }
}

class Desk{
    private List<String> list = new ArrayList<>();
    public synchronized void put(){
        try {
            String name = Thread.currentThread().getName();
            if(list.size() == 0){
                list.add(name + "---生产");
                System.out.println(name + "---生产");
                Thread.sleep(2000);
                // 唤醒别人
                this.notifyAll();
                // 进行等待
                this.wait();
            }else{
                // 唤醒别人
                this.notifyAll();
                // 进行等待
                this.wait();
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
    public synchronized void get(){
        try {
            String name = Thread.currentThread().getName();
            if(list.size() == 1){
                System.out.println(name + "---消费");
                list.clear();
                Thread.sleep(2000);
                this.notifyAll();
                this.wait();
            }else{
                this.notifyAll();
                this.wait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
