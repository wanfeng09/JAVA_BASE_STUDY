package com.unitOrReflection.ReflectionTest;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;

/*
* 反射的作用
* 基本作用：可以得到一个类的全部成分然后作用
* 可以破坏封装性
* 适合做Java框架，基本上，主流的框架都会基于反射设计出一些通用的功能
*
* 简易版框架【封装公共方法】
* 定义一个方法，可以接手任意对象
* 每收到一个对象后，使用反射获取该对象的Class对象，然后获取全部的成员变量
* 遍历成员变量，然后提取成员变量在该对象中的具体只。
* 把成员变量名、和其值，写出到文件中去即可。
*
* */
public class Action {
    public static void main(String[] args) throws Exception {
        Cat3 c = new Cat3("布偶",1);
        Cat3 c2 = new Cat3("英短",2);

        Common.saveObject(c);
        Common.saveObject(c2);
        System.out.println("写入完成");
    }
}


class Common{
    public static void saveObject(Object obj) throws Exception {
        // 高级流不支持追加数据，要转换原始流
        PrintStream ps = new PrintStream(new FileOutputStream("D:\\java\\demo\\IOtest\\a.txt",true));
        Class c = obj.getClass();
        String cName = c.getSimpleName();
        ps.println("-----------" + cName + "------------------");
        // 获取全部成员变量
        Field[] fields = c.getDeclaredFields();
        for(Field f : fields){
            String name = f.getName();
            f.setAccessible(true);
            String value = f.get(obj) + "";
            ps.println(name + "=" + value);
        }
        ps.close();
    }
}

class Cat3{
    private String name;
    private int age;

    public Cat3(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Cat3() {
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
}
