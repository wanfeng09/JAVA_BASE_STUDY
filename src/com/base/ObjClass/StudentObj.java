package com.base.ObjClass;
/*
* 万物皆对象（谁的数据谁处理），对象本质上是一种特殊的数据结构。
* 创建类（对象数据结构），new一个对象，调用对象中数据结构里的东西
* 类中定义的变量也称为成员变量（对象的属性），类中定义的方法也称为成员方法（对象的行为）。
* 成员变量的本身也存在默认值,不建议赋初始值。
* 一个代码中可以写多个class类，但是只能一个用public修饰。【public修饰的类名必须为代码文件名】
* 注意
* 当堆内存中的对象，如果没有任何变量引用（指向）时，就会被判定为内存中的垃圾。
* java存在自动垃圾回收机制，会自动清除掉垃圾对象，程序员不需要担心。
*
* this关键字
* this就是一个变量，可以在方法中，来拿到当前对象。
* 应用场景：解决变量名称冲突问题。【成员变量，方法形参名称相同】
* 私有成员变量【getter/setter方法】 右键-generate-getter/setter
 * */
public class StudentObj {
    String name; // 成员变量
    int age;
    private int sex; // 0男 1女 私有变量（通过get、set方法获取）
    public void test(){ // 成员方法
        System.out.println("姓名"+name+"--------"+age);
    }


    // 私有成员变量访问
    public int getSex() {
        return sex;
    }
    // 私有成员变量设置值
    public void setSex(int sex) {
        this.sex = sex;
    }

    public void ifSex(int sex){
        this.sex = sex;
        System.out.println("该学生性别是："+this.sex);
    }
}

class HasCat{

}
