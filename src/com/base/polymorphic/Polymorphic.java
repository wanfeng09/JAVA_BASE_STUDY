package com.base.polymorphic;
/*
* 多态是在继承/实现情况下的一种现象，表现为对象多态、行为多态.
* 多态的前提：有继承/实现关系，存在父类引用子类对象；存在方法重写.
* 多态是对象、行为的多态，java中的属性（成员变量）不谈多态。
*
* 好处
* 多态形势下：右边对象是解耦合的，更便于扩展和维护。
* 定义方法时，使用父类类型的形参，可以接受一切子类对象，扩展性更强，更便利。
*
* 注意
* 多态不能使用子类的独有功能。
* 类型转换
* 自动类型转换：父类 变量名 = new 子类();
* 强制类型转换： 子类 变量名 = (子类) 父类变量名
* 强制类型转换注意事项
* 存在继承/实现关系就可以在编译阶段进行强制类型转换，编译阶段不会报错。
* 运行时，如果发现对象的真实类型与强转后的类型不同，就会报类型转换异常（ClassCastException）的错误出来。
* 强转前，java建议：使用instanceof关键字，判断当前对象的真实类型，再进行强转。
* */
public class Polymorphic {
    public static void main(String[] args) {
        // 对象多态
        People p1 = new Teacher();
        People p2 = new Student();
        // 行为多态【编译看左边，运行看右边】
        // p1.run(); // Teacher
        // p2.run(); // Student
        // 编译运行都看左边
        // System.out.println(p1.name); // people
        // System.out.println(p2.name); // people

        // p2.test(); 报错【多态不能使用子类的独有功能。】
        // 解决
        Student s1 = (Student) p2;
        s1.test();

        // Teacher t1 = (Teacher) p2; // 报错 java.lang.ClassCastException
        // 解决
        if(p2 instanceof Student){
            Student s2 = (Student) p2;
        }else{
            Teacher t2 = (Teacher) p2;
        }
    }
}

class People{
    String name = "people";
    public void run(){
        System.out.println("people");
    }
}

class Teacher extends People{
    String name = "Teacher";
    @Override
    public void run(){
        System.out.println("Teacher");
    }
}

class Student extends People{
    String name = "Student";
    @Override
    public void run(){
        System.out.println("Student");
    }

    // 独有功能
    public void test(){
        System.out.println("学生独有功能");
    }
}
