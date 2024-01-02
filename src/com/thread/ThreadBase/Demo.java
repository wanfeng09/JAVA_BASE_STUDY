package com.thread.ThreadBase;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/*
* 线程（java.lang.Thread）
* 单线程 --- 主线程main
* 一个程序内部的一条执行流程
* 程序中如果只有一条执行流程，那这个程序就是单线程的程序
*
* 多线程
* 指软硬件上实现的多条执行流程的技术（多条线程有CPU负责调度执行）
*
* 实现多线程
* 方式1
* 定义一个子类继承实现Thread类，重写run()方法，无返回值
* new一个Thread类的对象
* 调用线程对象的start()方法启动线程【自动执行run()方法】
* 优点：编码简单
* 缺点：线程类已经继承Thread，无法继承其他类，不利于功能的扩展
*
* 方式2
* 定义一个类实现Runnable接口，重写run()方法，无返回值
* new一个Runnable任务对象，把任务对象交给一个线程对象处理
* 调用线程对象的start()方法启动线程【自动执行run()方法】
* 优点：任务类只是实现接口，可以继承其他类、实现其他接口、扩展性强
* 缺点：需要多一个Runnable对象
*
* 方式3
* 构造器
* FutureTask(Callable call) 把callable类型的对象封装成FutureTask对象
* 方法
* get() 获取执行call()方法返回的结果
*
* 定义一个类实现Callable接口，重写call()方法，和要返回的数据
* 把FutureTask任务对象交给线程对象处理
* 调用线程对象的start()方法启动线程
* 线程执行完毕后，通过FutureTask对象的get()方法去获取线程任务执行的结果
* 优点：可以继承类和实现接口，扩展性强，返回线程执行完毕后的结果
* 缺点：编码较复杂
*
*
* 注意事项
* 启动线程必须调用start()方法，不是调用run()方法
* 不要把主线程任务放在启动子线程之前【因为主线程任务执行完才会执行子线程，自上而下，依次执行】
*
* Thread提供常用构造器
* Thread(String name) 为当前线程指定名称
* Thread(Runnable r) / Thread(Runnable r,String name) 封装Runnable对象成为线程对象 / 并指定名称
* Thread(FutureTask f) 封装FutureTask对象成为线程对象
*
*
* Thread提供常用方法
* run() 线程的任务方法
* start() 启动线程【自动执行run()方法】
* getName() 获取当前线程的名称，线程名称默认是Thread-索引
* setName() 设置线程名称
* currentThread() 获取当前执行的线程对象
* sleep(long time) 让当前执行的线程休眠多少毫秒，在继续执行
* join() 让调用当前这个方法的线程先执行完
*
* Thread其他方法
* yield/interrupt/守护线程/线程优先级等线程的控制方法，在开发中很少使用。
*
*
*
* */
public class Demo {
    public static void main(String[] args) {
        // 方式1：继承Thread类
        extendsThreadTest();
        // 方式2：实现Runnable接口
        // RunnableThreadTest();
        // 方式3：实现Callable接口
        /*try {
            CallableThreadTest(); // get()方法导致子线程执行完毕，才会走main主线程执行任务
        }catch (Exception e){
            e.printStackTrace();
        }*/

        // 获取主线程对象
        // 那个线程执行它，它就会得到那个线程对象
        Thread mt = Thread.currentThread();
        System.out.println(mt.getName()); // main
        // 自定义线程名称
        mt.setName("主线程测试");
        System.out.println(mt.getName()); // 主线程测试


        // 主线程执行任务
        for (int i = 0; i < 6; i++) {
            System.out.println("主线程main执行中--" + i);
            if(i == 3){
                try {
                    // 让当前线程暂停3秒后继续执行
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void extendsThreadTest(){
        // 方式1：继承Thread类
        // 创建线程对象【自动执行run()方法】
        // Thread t1 = new ThreadTest1();
        Thread t1 = new ThreadTest1("线程1名称自定义");
        // 启动线程
        t1.start();
        // 让当前线程执行完成
        try {
            t1.join();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void RunnableThreadTest(){
        // 方式2：实现Runnable接口
        // Runnable r = new ThreadTest2();

        // 简化
        // 创建Runnable的匿名内部类对象
       /* Runnable r = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 6; i++) {
                    System.out.println("线程2执行中--" + i);
                }
            }
        };*/

        Runnable r = () -> {
            for (int i = 0; i < 6; i++) {
                System.out.println("线程2执行中--" + i);
            }
        };
        Thread t2 = new Thread(r);
        System.out.println("线程2名称----"+t2.getName()); // Thread-0
        t2.start();

    }

    public static void CallableThreadTest() throws Exception {
        // 方式3：实现Callable接口
        Callable<String> c = new ThreadTest3();
        // 把Callable对象封装成FutureTask对象【任务对象】
        FutureTask<String> f = new FutureTask<>(c);
        // 把任务对象交给线程对象
        Thread t3 = new Thread(f);
        t3.start();
        // 获取执行结果
        // 注意：这里代码会进行暂停，线程执行完毕才会获取结果
        String result = f.get();
        System.out.println(result);

    }
}

class ThreadTest1 extends Thread{
    public ThreadTest1(String name) {
        super(name); // 为当前线程设置名字
    }

    @Override
    public void run() {
        Thread t = Thread.currentThread();

        // 线程的执行任务
        for (int i = 0; i < 6; i++) {
           //  System.out.println("线程1执行中--" + i);
            System.out.println(t.getName() + "--" + i);

        }
    }
}

class ThreadTest2 implements Runnable{
    @Override
    public void run() {
        // 线程的执行任务
        for (int i = 0; i < 6; i++) {
            System.out.println("线程2执行中--" + i);
        }
    }
}

class ThreadTest3 implements Callable<String>{
    @Override
    public String call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 6; i++) {
            sum += i;
            System.out.println("线程3执行中--" + i);
        }
        return "线程3执行结果返回值：" + sum;
    }
}
