package com.advanced.ArrayAlgorithm;
/*
* copyOfRange(数组对象,起始索引，结束索引) --- 拷贝数组---（指定范围）包前不包后 --- 默认补0
* copyOf(数组对象,指定数组长度) --- 拷贝数组--- （指定数组长度）--- 默认补0
* Arrays.toString(数组对象) --- 返回数组内容
* setAll(double[] arr,IntToDoubleFunction generator) 把数组中的原数据改为新数组又存进去
*
* 数组对象排序
* 让该对象的类实现Comparable（比较规则接口），然后重写CompareTo方法，自己来制定比较规则
* 使用sort方法，创建Comparator比较器接口的匿名内部类对象，然后自己制定比较规则
* sort(arr, Comparator比较器)
*
* 比较规则
* 左大于右 --- 返回正整数
* 右大于左 --- 返回负整数
* 相等 --- 返回0
* */
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.IntToDoubleFunction;

public class CopyOfRangeDemo {
    public static void main(String[] args) {
        int[] arr = {10,20,30};
        // 返回数组内容
        System.out.println(Arrays.toString(arr)); // [10, 20, 30]

        // 拷贝数组
        int[] arr2 = Arrays.copyOfRange(arr,1,2);
        System.out.println(Arrays.toString(arr2)); // [20]

        // System.out.println(arr2); // [I@1b6d3586
        // System.out.println(arr); // [I@4554617c

        // System.out.println(arr == arr2); // false
        // System.out.println(arr.equals(arr2)); // false

        int[] arr3 = Arrays.copyOf(arr,10);
        System.out.println(Arrays.toString(arr3)); // [10, 20, 30, 0, 0, 0, 0, 0, 0, 0]

        System.out.println("====================================");

        Student[] stu = new Student[3];
        stu[0] = new Student(18,11.11,"小猫");
        stu[1] = new Student(22,12.02,"小狗");
        stu[2] = new Student(20,2.1,"小猪");
         Arrays.sort(stu);  // 无法排序对象数组 [重写比较规则 compareTo] 根据年龄
         System.out.println(Arrays.toString(stu));
        Arrays.sort(stu, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return Double.compare(o2.getHeight(), o1.getHeight()); // 降序
                // return Double.compare(o1.getHeight(), o2.getHeight()); // 根据高度 【升序】
            }
        });
        System.out.println(Arrays.toString(stu));

        System.out.println("-----------------------");
        double[] prices = {20.1,22,12.3};
        Arrays.setAll(prices, new IntToDoubleFunction() {
            @Override
            public double applyAsDouble(int value) {
                // value数组索引，默认会自动遍历 0,1,2
                // return prices[value] * 0.8; // 失精 [16.080000000000002, 17.6, 9.840000000000002]
                BigDecimal price = BigDecimal.valueOf(prices[value]);
                BigDecimal mul = BigDecimal.valueOf(0.8);
                BigDecimal newVal = price.multiply(mul);
                return newVal.doubleValue(); // [16.08, 17.6, 9.84]
            }
        });
        System.out.println(Arrays.toString(prices));

    }
}

class Student implements Comparable<Student>{
    private int age;
    private double height;

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student(int age, double height, String name) {
        this.age = age;
        this.height = height;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }
// 制定比较规则

    @Override
    public int compareTo(Student o) {
        return this.age - o.age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", height=" + height +
                ", name='" + name + '\'' +
                '}';
    }
}
