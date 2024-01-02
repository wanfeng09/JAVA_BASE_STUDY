package com.advanced.LambdaDemo;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.IntToDoubleFunction;

/*
* Lambda表达式【匿名内部类】
* 用于简化匿名内部类的代码写法
* 格式
* (被重写方法的形参列表)->{被重写的方法体代码}
*
* 函数式接口
* 有且仅有一个抽象方法的接口
* 注意：大部分函数式接口，上面都会有@FunctionalInterface的注解
*
* Lambda表达式的省略写法【进一步简化】
* 参数类型可以省略不写
* 如果只有一个参数，参数类型可以省略，同时()也可以省略
* 方法体只有一行，大括号可以不写，同时要省略分号，如果是return语句，也必须去掉return
*
* 特定类型的方法引用
* 如果某个Lambda表达式里只是调用一个实例方法，并且前面的参数列表中的第一个参数是作为方法的主调，
* 后面的所有参数都是作为该实例方法的入参，则此时就可以使用特定类型的方法引用。
* 类型::方法
*
* 实例方法的引用
* 如果某个Lambda表达式里只是调用一个实例方法，并且前后参数的形式一致，就可以使用实例方法引用
* 对象名::实例方法
*
* 静态方法的引用
* 如果某个Lambda表达式里只是调用一个静态方法，并且前后参数的形式一致，就可以使用静态方法的引用
* 类名::静态方法
*
* 构造器引用
* 如果某个Lambda表达式里只是在创建对象，并且前后参数情况一致，就可以使用构造器引用
* 类名::new
* */
public class Demo {
    public static void main(String[] args) {
        // 完整版在com.advanced.ArrayAlgorithm; CopyOfRangeDemo
        double[] prices = {20.1,22,12.3};
        Arrays.setAll(prices, value -> {
            // value数组索引，默认会自动遍历 0,1,2
            // return prices[value] * 0.8; // 失精 [16.080000000000002, 17.6, 9.840000000000002]
            BigDecimal price = BigDecimal.valueOf(prices[value]);
            BigDecimal mul = BigDecimal.valueOf(0.8);
            BigDecimal newVal = price.multiply(mul);
            return newVal.doubleValue(); // [16.08, 17.6, 9.84]
        });
        System.out.println(Arrays.toString(prices));
        System.out.println("---------------");

        String[] names = {"body","html","Div","p","span"};
        // 忽略大小写进行排序
        /*Arrays.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        });*/
        // 简化1
        // Arrays.sort(names, (o1, o2) -> o1.compareToIgnoreCase(o2) );
        // 特定类型的方法引用  类型::方法
        Arrays.sort(names, String::compareToIgnoreCase );
        System.out.println(Arrays.toString(names)); // [body, Div, html, p, span]

        Car[] carr = new Car[3];
        carr[0] = new Car("body");
        carr[1] = new Car("html");
        carr[2] = new Car("Aa");
        Car cpare = new Car();
       /* Arrays.sort(carr, new Comparator<Car>() {
            @Override
            public int compare(Car o1, Car o2) {
                return cpare.compareName(o1,o2);
            }
        });*/

        // 实例方法的引用 对象名::实例方法
        Arrays.sort(carr, cpare::compareName);
        System.out.println(Arrays.toString(carr));
        // 静态方法的引用 类名::静态方法
        Arrays.sort(carr, Car::compareByName);
        System.out.println(Arrays.toString(carr));

        System.out.println("--------------------");

        /*CreateCar cc = new CreateCar() {
            @Override
            public Car create(String name) {
                return new Car(name);
            }
        };
        Car c6 = cc.create("猫猫");
        System.out.println(c6); // Car{name='猫猫'}*/

        // CreateCar cc = (name)  -> new Car(name);

        // 构造器引用 类名::new
        CreateCar cc = Car::new;
        Car c6 = cc.create("猫猫");
        System.out.println(c6); // Car{name='猫猫'}
    }

}

interface CreateCar{
    Car create(String name); // 等同于 public abstract Car create();
}

class Car{
    private String name;

    // 静态方法
    public static int compareByName(Car o1,Car o2){
        return o1.getName().compareTo(o2.getName());
    }

    // 实例方法
    public int compareName(Car o1,Car o2){
        return o1.getName().compareTo(o2.getName());
    }

    public Car(String name) {
        this.name = name;
    }

    public Car() {
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }
}
