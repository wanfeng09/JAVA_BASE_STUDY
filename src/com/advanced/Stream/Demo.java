package com.advanced.Stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
* Stream：用来操作集合或者数组的数据
* Stream流大量的结合了Lambda的语法风格来编程，提供了一种更加强大，更加简便的方式操作集合或者数组中的数据，代码更简洁，可读性更好
* 操作步骤
* 获取当前集合的Stream流 stream()，获取当前数组的Stream流 Arrays.stream(arr) / Stream.of(arr)
* 调用中间方法对数据进行处理、计算（过滤、排序、去重） filter()
* 获取处理结果（终结方法），收集到一个新集合返回 collect()
*
* 常见的中间方法
* filter() 用于对流中的数据进行过滤
* sorted() 对元素进行升序排序【可自定义规则】
* limit(long maxSize) 获取前几个元素
* skip(long n) 跳过前几个元素
* distinct() 去除流中重复的元素
* map() 对元素进行加工，返回对应的新流
* concat() 合并a和b两个流为一体
*
* 终结方法
* forEach() 遍历
* count() 统计流运算后的元素个数
* max() 获取流运算后的最大值元素
* min() 获取流运算后的最小值元素
* 收集Stream流：把Stream流操作后的结果转回到集合或者数组中去返回
* Stream流：方便操作集合、数组的手段；集合、数组：才是开发的目的
* collect() 把流处理后的结果收集到一个指定的集合中去 toList() toSet() toMap()
* toArray() 把流处理后的结果收集到一个数组中去
*
* 注意：流只能收集一次，不能复用
*
*
* */
public class Demo {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        Collections.addAll(names,"aaa","nnn","ccc","add","eee");
        System.out.println(names); // [aaa, nnn, ccc, add, eee]

        // 找出包含a元素，存入在一个新集合中去
        List<String> newList = new ArrayList<>();
        for (String ele:names){
            if(ele.startsWith("a")){
                newList.add(ele);
            }
        }
        System.out.println(newList); // [aaa, add]

        // Stream流【可链式】
        List<String> newList2 = names.stream().filter(s -> s.startsWith("a")).collect(Collectors.toList());
        System.out.println(newList2); // [aaa, add]

        // 获取当前数组的Stream流
        String[] arr = {"aa","nnn","bb"};
        // Stream<String> s = Arrays.stream(arr);
        Stream<String> s = Stream.of(arr).filter(ele -> ele.startsWith("a"));
        // 流只能收集一次，不能复用
        System.out.println("========================");
        // System.out.println(s.collect(Collectors.toList())); // [aa]
        System.out.println(s.collect(Collectors.toSet())); // [aa] // 报错IllegalStateException
        System.out.println("========================");
        List<Cat> cats = new ArrayList<>();
        cats.add(new Cat("布偶",8,1668.88));
        cats.add(new Cat("布偶",8,1668.88));
        cats.add(new Cat("狸花",5,1868.88));
        cats.add(new Cat("狸花",8,1168.88));
        cats.add(new Cat("三花",7,1268.66));

        // 获取价格最低的2只猫
        cats.stream().sorted((o1,o2) -> Double.compare(o1.getPrice(),o2.getPrice())).skip(cats.size() - 2).forEach(System.out::println);
        System.out.println("---------------");

        // 去重，重写hasCode和equals方法
        cats.stream().distinct().forEach(System.out::println);
        System.out.println("---------------");


        // 合并
        Stream<String> st1 = Stream.of("测试1","测试2");
        Stream<String> st2 = Stream.of("测试8","测试6","测试6");
        Stream<String> all = Stream.concat(st1,st2);
        all.forEach(System.out::println);

        // 获取流运算后的最大值元素
        Cat max =  cats.stream().max((o1,o2) -> Integer.compare(o1.getAge(), o2.getAge())).get();
        Cat min =  cats.stream().min((o1,o2) -> Integer.compare(o1.getAge(), o2.getAge())).get();
        System.out.println(max);
        System.out.println(min);

        // 获取年龄等于等于8个月有几只
        long size = cats.stream().filter(e -> e.getAge() == 8).count();
        System.out.println(size);

        // 把流处理后的结果收集到一个指定的集合中去 toList() toSet() toMap()
        List<Cat> catList = cats.stream().filter(ele -> ele.getAge() == 8).collect(Collectors.toList());
        System.out.println(catList); // [Cat{name='布偶', age=8, price=1668.88}, Cat{name='布偶', age=8, price=1668.88}, Cat{name='狸花', age=8, price=1168.88}]

        Set<Cat> catSet = cats.stream().filter(ele -> ele.getAge() == 8).collect(Collectors.toSet());
        System.out.println(catSet); // [Cat{name='狸花', age=8, price=1168.88}, Cat{name='布偶', age=8, price=1668.88}]

        // 键不可重复，否则报错，distinct()去重
        Map<String,Integer> catMap = cats.stream().filter(ele -> ele.getAge() == 8).distinct().collect(Collectors.toMap(a->a.getName(),a->a.getAge()));
        System.out.println(catMap); // {狸花=8, 布偶=8}

        // toArray() 把流处理后的结果收集到一个数组中去
        Object[] objArr = cats.stream().filter(ele -> ele.getAge() == 8).toArray();
        System.out.println(Arrays.toString(objArr));

        Cat[] catArr = cats.stream().filter(ele -> ele.getAge() == 8).toArray(len -> new Cat[len]); // len数组长度
        System.out.println(Arrays.toString(catArr));


    }
}


class Cat{
    private String name;
    private int age;
    private double price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return age == cat.age && Double.compare(price, cat.price) == 0 && Objects.equals(name, cat.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, price);
    }

    public Cat() {
    }

    public Cat(String name, int age, double price) {
        this.name = name;
        this.age = age;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", price=" + price +
                '}';
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
