package com.advanced.ToStringDemo;

import java.util.Objects;

/*
* Object中toString方法作用：返回对象的字符串形式
* 可以让子类重写，返回子类对象内容
* equals方法：默认比较两个对象地址是否相等。
* 可以让子类重写，比较两个对象的内容是否相同。
* clone方法：对象克隆，复制一个一模一样的新对象
* 浅克隆：拷贝出新对象，与原数据中的数据一模一样【引用类型的地址也一样】
* 深克隆：对象基本类型直接拷贝，字符串拷贝的地址是一样的，引用类型地址会重新创建新对象。
* 重写克隆方法：当前类要实现一个标记接口Cloneable【规则】,调用抛出异常
*
* */
public class Demo {
    public static void main(String[] args) throws CloneNotSupportedException {
        String s1 = "hello";
        String s2 = "hello";
        System.out.println(Objects.equals(s1,s2)); // true

        String s3 = null;
        String s5 = "hello";
        System.out.println(Objects.equals(s3,s5)); // false
        // 判断是否为null
        System.out.println(Objects.isNull(s3));// true
        System.out.println(s3 == null);// true

        // 判断是否不为null
        System.out.println(Objects.nonNull(s3));// false
        System.out.println(Objects.nonNull(s5));// true

        // 对象克隆
        // protected Object clone()
        User user1 = new User("name",18,new int[]{2,3,8});
        User user2 = (User) user1.clone();
        System.out.println(user1.getName());
        System.out.println(user1.getList());
        System.out.println(user2.getName());
        System.out.println(user2.getList());

    }
}

// Cloneable是一个标记接口
// 规则
class User implements Cloneable{
    private String name;
    private int age;
    private int[] list;

    public User() {
    }

    // 重写克隆方法【构造器生成】
//    @Override
//    protected Object clone() throws CloneNotSupportedException {
//        // super去调用父类object中的clone方法
//        return super.clone(); // 浅克隆
//    }

    // 深克隆
    @Override
    protected Object clone() throws CloneNotSupportedException {
        // super去调用父类object中的clone方法
       User u2 = (User) super.clone();
       u2.list = u2.list.clone();
       return u2;
    }

    public User(String name, int age, int[] list) {
        this.name = name;
        this.age = age;
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int[] getList() {
        return list;
    }

    public void setList(int[] list) {
        this.list = list;
    }


}
