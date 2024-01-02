package com.proxy.Base;

import ch.qos.logback.classic.spi.ThrowableProxyUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/*
* 代理
* 实现代理需要通过接口实现
* 对象如果存在多个执行的事，可以通过代理来转移部分职责
* 对象如果有什么方法想被代理，代理就一定要有对应的方法
*
* 创建代理对象
* java.lang.reflect.Proxy类：提供了为对象产生代理对象的方法
* newProxyInstance(ClassLoader loader,Class<?>{} interfaces,InvocationHandler h)
* ClassLoader loader：用于指定一个类加载器
* Class<?>{} interfaces：指定生成的代理存在那些方法
* InvocationHandler h：指定生成的代理对象要干什么事
*
*
* */
public class Demo {
   /* public static void main(String[] args) {
        proxyEvent p = new proxyEvent("Xia");
        p.print();
        String rs = p.run("Xia");
        System.out.println(rs);


    }*/

    // 创建代理
    public static proxyMedium createProxy(proxyEvent p){
        /*
        *
        * newProxyInstance(ClassLoader loader,Class<?>{} interfaces,InvocationHandler h)
        * ClassLoader loader：用于指定一个类加载器
        * Class<?>{} interfaces：指定生成的代理存在那些方法
        * InvocationHandler h：指定生成的代理对象要干什么事
        *
        * */
        proxyMedium pm = (proxyMedium) Proxy.newProxyInstance(ThrowableProxyUtil.class.getClassLoader(), new Class[]{proxyMedium.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 代理对象要做的事情
                if(method.getName().equals("run")){
                    System.out.println("代理run方法");
                }else if(method.getName().equals("print")){
                    System.out.println("代理print方法");
                }
                return method.invoke(p,args);
            }
        });
        return pm;
    }
}

/**
 * 代理媒介
 */
interface proxyMedium{
    String run(String name);
    void print();
}

/**
 * 代理事件【实现接口】
 */
class proxyEvent implements proxyMedium{
    private String name;

    public proxyEvent(String name) {
        this.name = name;
    }

    public String run(String name){
        return name + "代理执行了任务!";
    }

    public void print(){
        System.out.println("代理打印了事件！");
    }
}
