package com.base.MethodTest;

/*
* 方法是一种语法结构，他可以将一段代码封装成一个功能，已便重复调用。【复用性】
* 方法: 栈内存先进后出，最后处理完main输出清空栈内存了，一个方法执行完成就会从栈内存中移除。
* 方法修饰符：public static
* 格式：
* 修饰符 返回值类型 方法名（形参列表）{
*       方法体代码【需要执行的功能代码】
*       return 返回值；
* }
* 方法的返回值类型写void(无返回值)时，方法内不能使用return返回数据
* 如果方法的返回值类型写了具体类型，方法内部则必须使用return返回对应的数据类型
* 调用无返回值的方法，只能直接调用，不能变量接收值。
*
* 方法重载：一个类中，出现多个方法的名称相同，但是它们的形参列表不同。（修饰符跟返回类型是否一样都无所谓）
* 形参列表不同：个数，类型，顺序不同，不关系形参名称
* */
public class MethodTest {
    public static void main(String[] args) {
        int a = sum(10,20);
        System.out.println(a);
        NoReturn();
        medthodreLoad();
        System.out.println(medthodreLoad(10));
    }
    // 方法定义
    public static void NoReturn(){
        System.out.println("hello word");
    }

    public  static int sum(int a,int b){
        return a+ b;
    }

    // 方法重载
    public  static void medthodreLoad(){
        System.out.println("c测试");
    }
    public  static int medthodreLoad(int a){
        return a;
    }

     void medthodreLoad(int a, String name){
         System.out.println("c测试" + a + name);
    }
}
