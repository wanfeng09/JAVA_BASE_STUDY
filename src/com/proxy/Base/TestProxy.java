package com.proxy.Base;

/**
 * 测试代理
 */
public class TestProxy {
    public static void main(String[] args) {
        proxyEvent proxyEvent = new proxyEvent("LuXi");
        proxyMedium proxyMedium = Demo.createProxy(proxyEvent);
        String rs = proxyMedium.run("LuXi");
        System.out.println(rs);
    }
}
