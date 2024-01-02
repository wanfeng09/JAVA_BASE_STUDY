package com.thread.threadPool;

import java.util.concurrent.*;
/*
* 线程池 ExecutorService接口
* 可复用线程技术
* 避免产生大量的线程，影响系统性能
* 任务接口 Runnable/Callable
*
* 创建线程池对象
* 方式1：使用ExecutorService的实现类ThreadPoolExecutor创建一个线程池对象
* 构造器
* ThreadPoolExecutor(int corePoolSize,int maxPoolSize,long keepAliveTime,TimeUnit unit,BlockingQueue<Runnable> workQueue,ThreadFactory t,RejectedExecutionHandler handler)
* 参数说明
* int corePoolSize 指定线程池的核心线程的数量【正式工：3】
* int maxPoolSize 最大线程数量【员工数：5，临时工：2】
* long keepAliveTime 指定临时线程的存活时间【临时工空闲多久被开除】
* TimeUnit unit 指定临时线程存活的时间单位（秒分时天）
* BlockingQueue<Runnable> workQueue 指定线程池的任务队列【客户排队】
* ThreadFactory t 指定线程池的线程工厂 【招聘员工hr】
* RejectedExecutionHandler handler 指定线程池的任务拒绝策略（线程都在忙，任务队列也满了，新任务来了该怎么处理）【忙不过来怎么办？】
*
* 方式2：使用Executors（线程池的工具类）调用方法返回不同特点的线程池对象
* 方法
* newFixedThreadPool(int nThreads) 创建固定线程数量的线程池，如果某个线程因为执行异常而结束，那么线程池会补充一个新线程替代它。
* newSingleThreadExecutor() 创建只有一个线程的线程池对象，如果该线程出现异常而结束，那么线程池会补充一个新线程
* newCachedThreadPool() 线程数量随着任务增加而增加，如果线程任务执行完毕且空闲了60s则会被回收掉
* newScheduledThreadPool(int corePoolSize) 创建一个线程池，可以实现在给定的延迟后运行任务，或者定期执行任务
*
* 注意：以上方法底层都是通过线程池的实现类ThreadPoolExecutor创建的线程池对象
*
* 风险：大型并发系统环境中使用Executors如果不注意可能会出现系统风险。
* 线程池不允许使用Executors去创建，而是通过ThreadPoolExecutor的方式，规避资源耗尽的风险。
* Executors返回线程池对象的弊端
* FixedThreadPool和SingleThreadPool: 允许的请求队列长度为Integer.MAX_VALUE,可能会堆积大量的请求，从而导致OOM。
* CachedThreadPool和ScheduledThreadPool：允许的创建线程数量为Integer.MAX_VALUE，可能会创建大量的线程，从而导致OOM。
*
* 核心线程数量如何配置？
* CPU：内核、逻辑处理器（线程数量看这个）
* 计算密集的任务：核心线程数量 = CPU的核数 + 1
* IO密集型任务：核心线程数量 = CPU的核数 + 2
*
* 线程池注意事项
* 临时相册什么时候创建？
* 新任务提交时发现核心线程都在忙，任务队列也满了，并且还可以创建临时线程，此时才会创建临时线程
*
* 什么时候会开始拒绝新任务？
* 核心线程和临时线程都在忙，任务队列也满了，新任务过来的时候才会开始拒绝任务。
*
* ExecutorService常用方法
* execute(Runnable command) 执行Runnable任务
* Future<T> submit(Callable<T> task) 执行callable任务，返回未来任务对象，用于获取线程返回的结果
* shutdown() 等全部任务执行完毕后，再关闭线程池！
* shutdownNow() 立刻关闭线程池，停止正在执行的任务，并返回队列中未执行的任务
*
* 新任务拒绝策略
* ThreadPoolExecutor.AbortPolicy() 丢弃任务并抛出RejectedExecutionException异常，是默认的策略
* ThreadPoolExecutor.DiscardPolicy() 丢弃任务，但是不抛出异常。【不推荐】
* ThreadPoolExecutor.DiscardOldestPolicy() 抛弃队列中等待最久的任务，然后把当前任务加入队列中
* ThreadPoolExecutor.CallerRunsPolicy() 又主线程负责调用任务的run()方法从而绕过线程池直接执行
*
*
*
*
* */
public class Demo {
    public static void main(String[] args) throws Exception {
        // 创建线程池对象
        // 方式1：使用ExecutorService的实现类ThreadPoolExecutor创建一个线程池对象
        // new LinkedBlockingQueue<>()
      /* ExecutorService pool = new ThreadPoolExecutor(3,5,8, TimeUnit.SECONDS,new ArrayBlockingQueue<>(4), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());*/
       // Runnable r = new RunnableTest(); // 不需要写了
        // 方式2：使用Executors（线程池的工具类）调用方法返回不同特点的线程池对象
        ExecutorService pool = Executors.newFixedThreadPool(21); // CPU的核数 + 1
      /* 处理Runnable任务
      // 核心线程-3
       pool.execute(r); // 线程池会自动创建一个新线程，自动处理这个任务，自动执行！
       pool.execute(r); // 线程池会自动创建一个新线程，自动处理这个任务，自动执行！
       pool.execute(r); // 线程池会自动创建一个新线程，自动处理这个任务，自动执行！
        // 任务队列-4
       pool.execute(r); // 复用前面的核心线程
       pool.execute(r); // 复用前面的核心线程
       pool.execute(r);
       pool.execute(r);
       // 临时线程创建时机
       pool.execute(r);
       pool.execute(r);
       // 新任务拒绝时机【新任务拒绝策略】
       // RejectedExecutionException 拒绝执行
       pool.execute(r);*/

        // 处理 Callable 任务
        Future<String> f1 = pool.submit(new CallableTest());
        Future<String> f2 = pool.submit(new CallableTest());
        Future<String> f3 = pool.submit(new CallableTest());
        System.out.println(f1.get());
        System.out.println(f2.get());
        System.out.println(f3.get());

        // 线程池不会自动关闭。
        // 等待线程池的任务全部执行完毕，再关闭线程
        pool.shutdown();
        // 立即关闭！不管任务是否执行完毕！
        // pool.shutdownNow(); // 中断异常
    }
}

class RunnableTest implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

class CallableTest implements Callable<String>{
    @Override
    public String call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 6; i++) {
            sum += i;
//            System.out.println(Thread.currentThread().getName() + "线程执行中--" + i);
        }
        return Thread.currentThread().getName() + "执行结果返回值：" + sum;
    }
}
