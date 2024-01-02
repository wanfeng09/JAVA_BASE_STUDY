package com.proxy.Anli;

public class MethodTest {
    public static void main(String[] args) throws Exception {
      ProxyAnLiInterface p = ProxyAnLiTest.createProxyAnLi(new ProxyAnLiEvent());
      p.test1();
      System.out.println("-----------------------");
      p.test2();
      System.out.println("-----------------------");
      p.test3();
    }
}


