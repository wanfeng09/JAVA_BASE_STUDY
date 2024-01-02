package com.proxy.Anli;

import ch.qos.logback.classic.spi.ThrowableProxyUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyAnLiTest {
    public static ProxyAnLiInterface createProxyAnLi(ProxyAnLiEvent event){
        ProxyAnLiInterface pf = (ProxyAnLiInterface) Proxy.newProxyInstance(ThrowableProxyUtil.class.getClassLoader(), new Class[]{ProxyAnLiInterface.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                long start = System.currentTimeMillis();
                Object rs = method.invoke(event,args);
                long end = System.currentTimeMillis();
                System.out.println( method.getName()+ "耗时了" + (end - start) / 1000.0 + "s");
                return rs;
            }
        });
        return pf;
    }
}

interface ProxyAnLiInterface{
    void test1() throws InterruptedException;
    void test2();
    void test3();
}

class ProxyAnLiEvent implements ProxyAnLiInterface{
    public int count;

    @Override
    public void test1() throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            count++;
        }
        Thread.sleep(2000);
    }

    @Override
    public void test2() {
        for (int i = 0; i < 60000; i++) {
            count++;
        }
    }

    @Override
    public void test3() {
        for (int i = 0; i < 30000000; i++) {
            count++;
        }
        System.out.println(count);
    }
}
