package com.thread.ThreadSafe;
/*
* 线程安全问题
* 存在多个线程在同时执行
* 同时访问一个共享资源
* 存在修改该共享资源
*
* 解决线程安全问题
* 线程同步：让多个线程实现先后依次访问共享资源
* 作用：把访问共享资源的核心代码上锁，以此保证线程安全
* 原理：每次只允许一个线程加锁后进入，执行完毕后自动解锁，其他线程才可以进来进行
* 方式1：同步代码块
* synchronized(同步锁){}
* 同步锁
* 实例方法建议使用this作为锁对象
* 静态方法建议使用字节码（类名.class）对象作为锁对象
*
* 注意：对于当前同时执行的线程来说，同步锁必须是同一把（同一个对象），否则会出bug。
*
* 方式2：同步方法
* 修饰符 synchronized 返回值类型 方法名称(形参){}
* 底层原理
* 同步方法底层也是有隐式锁对象，锁的范围是整个方法代码
* 如果方法是实例方法，同步方法默认使用this作为锁对象
* 如果方法是静态方法，同步方法默认使用字节码（类名.class）作为锁对象
*
* 方式3：Lock锁
* 创建锁对象进行加锁和解锁，更灵活、更方便、更强大
* 构造器
* ReentrantLock() 获取Lock锁的实现类对象
* 方法
* lock() 加锁
* unlock() 解锁
*
*
* 同步代码块与同步方法区别
* 范围上：同步代码块锁的范围更小
* 可读性：同步方法更好
*
* */

      /*  错误的执行结果
        账号2来取钱100.0成功
        账号1来取钱100.0成功
        剩余：-100.0元
        剩余：0.0元*/

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*正确的执行结果
        账号1来取钱100.0成功
        剩余：0.0元
        账号2，余额不足！*/
public class Demo {
    public static void main(String[] args) {
        // 共享账号【唯一性】
        Account account = new Account("Account1",100);
        // 创建线程对象
        Runnable r1 = new operateThread(account);
        Thread t1 = new Thread(r1,"账号1");
        Runnable r2 = new operateThread(account);
        Thread t2 = new Thread(r2,"账号2");
        // 开启线程
        t1.start();
        t2.start();
    }
}


// 共享资源
class Account{
    private String cardId; // 卡号
    private double money; // 余额
    // 方式3：Lock锁
    private final Lock lock = new ReentrantLock(); // 唯一性
    // 方式3：Lock锁
    public void getMoneyChange(double m){
        // 获取谁取钱
        String name = Thread.currentThread().getName();
        // 加锁
        lock.lock();
        if(this.money >= m){
            System.out.println(name + "来取钱" + m + "成功");
            this.money -= m;
            System.out.println("剩余：" + this.money + "元");
        }else {
            System.out.println(name + "，余额不足！");
        }
        // 解锁
        lock.unlock();
    }

    // 静态方法建议使用字节码（类名.class）对象作为锁对象
    public static void getMoneyChange2(double m){
        synchronized (Account.class){

        }
    }

    // 取钱操作【错误的逻辑】
   /* public void getMoneyChange(double m){
        // 获取谁取钱
        String name = Thread.currentThread().getName();
        if(this.money >= m){
            System.out.println(name + "来取钱" + m + "成功");
            this.money -= m;
            System.out.println("剩余：" + this.money + "元");
        }else {
            System.out.println(name + "，余额不足！");
        }
    }*/

    // 方式1：同步代码块
    // 实例方法建议使用this作为锁对象
    /*public void getMoneyChange(double m){
        // 获取谁取钱
        synchronized (this) { // this代表共享资源
            String name = Thread.currentThread().getName();
            if(this.money >= m){
                System.out.println(name + "来取钱" + m + "成功");
                this.money -= m;
                System.out.println("剩余：" + this.money + "元");
            }else {
                System.out.println(name + "，余额不足！");
            }
        }
    }*/

    // 方式2：同步方法
    /*public synchronized void getMoneyChange(double m){
        // 获取谁取钱
        String name = Thread.currentThread().getName();
        if(this.money >= m){
            System.out.println(name + "来取钱" + m + "成功");
            this.money -= m;
            System.out.println("剩余：" + this.money + "元");
        }else {
            System.out.println(name + "，余额不足！");
        }
    }*/



    public Account() {
    }

    public Account(String cardId, double money) {
        this.cardId = cardId;
        this.money = money;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}


// 线程
class operateThread implements Runnable{
    private Account a;

    public operateThread(Account a) {
        this.a = a;
    }

    @Override
    public void run() {
        // 取钱操作
        a.getMoneyChange(100);
    }
}
