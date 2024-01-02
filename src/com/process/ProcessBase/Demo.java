package com.process.ProcessBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/*
* 进程
* 正在运行的程序（软件）就是一个独立的进程。【如：微信、qq、IDEA】
* 线程是属于进程的，一个进程可以同时运行很多个线程。
* 进行中的多个线程其实是并发和并行执行的。
*
* 并发
* 进行中的线程是由CPU负责调度执行的，但CPU能同时处理线程的数量有限，为了保证全部线程都能往前执行，
* CPU会轮询为系统的每个线程服务，由于CPU切换的速度很快，给我们的感觉这些线程在同时执行，这就是并发。
*
* 并行
* 在同一时刻上，同时有多个线程在被CPU调度执行。
*
* 多线程执行原理：并发和并行同时进行。
*
* 线程的生命周期
* 线程的6种状态互相转换，定义在Thread类的内部枚举中
* |线程状态|说明|
* |NEW（新建）| 线程刚被创建，但是并未启动。|
* |Runnable（可运行）| 线程已经调用了start()，等待CPU调度。|
* |Blocked（锁阻塞）| 线程在执行的时候未竞争到锁对象，则该线程进行Blocked状态。|
* |Waiting（无限等待）| 一个线程进入Waiting状态，另一个线程调用notify或者notifyAll方法才能够唤醒。|
* |Timed Waiting（计时等待）| 同Waiting状态，有几个方法（sleep,wait）有超时参数，调用它们将进入Timed Waiting状态。|
* |Terminated（被终止）| 因为run方法正常退出而死亡，或者因为没有捕获的异常终止了run方法而死亡。|
*
* 乐观锁：一开始不上锁，认为没有问题，等到出现线程安全问题
* 悲观锁：一上来就加锁，每次只能一个线程进入
*
*
*
* */
public class Demo {
    public static void main(String[] args) throws Exception {
        // 100个线程，每个线程对其加100次。
       /* Runnable r = new numTest();
        for (int i = 0; i < 100; i++) {
            new Thread(r).start();
        }*/

        // 共享资源【100份数据】
        List<String> list = new ArrayList<>();
        String[] arr = {"数据1","数据2","数据6","数据8","数据9","数据7"};
        Random r = new Random();
        for (int i = 0; i < 100; i++) {
            list.add(arr[r.nextInt(arr.length)]);
        }
        System.out.println(list);

        // 定义线程
        listTest l1 = new listTest(list);
        Thread t1 =  new Thread(l1,"线程1");
        t1.start();
        listTest l2 = new listTest(list);
        Thread t2 =  new Thread(l2,"线程2");
        t2.start();

        t1.join();
        t2.join();
        System.out.println(l1.getCount());
        System.out.println(l2.getCount());



    }
}


class listTest implements Runnable{
    private List<String> list;
    private int count;

    public listTest(List<String> list) {
        this.list = list;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        Random r = new Random();
        while (true){
            synchronized (list){ // 锁唯一性，不能用this【listTest】，new了两次，不是唯一性了
                if(list.size() <= 10){
                    break;
                }
                String rs = list.remove(r.nextInt(list.size()));
                System.out.println(name + "选择了--" + rs);
                count++;
            }
        }

    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


}


class numTest implements Runnable{
    // 悲观锁
    //  private int count;
    // 乐观锁
    private AtomicInteger count = new AtomicInteger();
    @Override
    public void run() {
 /* for (int i = 0; i < 100; i++) {
                    System.out.println("count-----" + (++count)); // 会出现丢失  正确数据10000，错误9999
                }*/
        // 悲观锁
               /* synchronized (this) {
                    for (int i = 0; i < 100; i++) {
                        System.out.println("count-----" + (++count)); // 正确数据10000
                    }
                }*/
        // 乐观锁
        for (int i = 0; i < 100; i++) {
            System.out.println("count-----" + count.incrementAndGet()); // 正确数据10000
        }
    }
}
