package com.advanced.CollectionsDemo;

import java.util.*;

/*
* Collections
* 是一个用来操作集合的工具类
* 常用静态方法
* addAll(Collection<? super T> c,T...element) 给集合批量添加元素
* shuffle(List<?> list) 打乱List集合的元素顺序
* sort(List<?> list) 对List集合中的元素进行升序排序
* sort(List<?> list,Comparator<? super T> c) 对List集合中的元素，按照比较对象指定的规则进行排序
*
* 集合嵌套：集合中的元素又是一个集合
*
*
*
* */
public class Demo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        // 给集合批量添加元素
        Collections.addAll(list,"aaa","bbb","vvv","ccc");
        System.out.println(list); // [aaa, bbb, vvv, ccc]

        // 打乱List集合的元素顺序
        Collections.shuffle(list);
        System.out.println(list); // [ccc, vvv, bbb, aaa]

        System.out.println("---------------------");

        // 集合嵌套
        Map<String,List<String>> map1 = new HashMap<>();
        List<String> list1 = new ArrayList<>();
        Collections.addAll(list1,"aaa","nnn","ccc","ddd");
        map1.put("数据1",list1);
        System.out.println(map1); // {数据1=[aaa, nnn, ccc, ddd]}

    }
}
