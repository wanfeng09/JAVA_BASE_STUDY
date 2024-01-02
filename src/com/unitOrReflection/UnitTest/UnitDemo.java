package com.unitOrReflection.UnitTest;

import org.junit.*;

public class UnitDemo {
    @BeforeClass
    public static void testBeforeClass(){
        System.out.println("测试Class之前执行");
    }

    @AfterClass
    public static void testAfterClass(){
        System.out.println("测试Class之后执行");
    }

    @After
    public void testAfter(){
        System.out.println("测试之后执行");
    }

    @Before
    public void testBefore(){
        System.out.println("测试之前执行");
    }
    @Test // 测试方法
    public void unittest1(){
        Demo.test1("abc");
        Demo.test1(null);
    }

    @Test
    public void unitTest2(){
       int i=  Demo.test2("ccc");
        System.out.println(i); // 3
        int i2=  Demo.test2(null);
        System.out.println(i2); // -1
        // 断言机制，通过预测业务方法结果
        //Assert.assertEquals("方法内部bug！",4,i);
        Assert.assertEquals("ccc",3,i);
    }


}
