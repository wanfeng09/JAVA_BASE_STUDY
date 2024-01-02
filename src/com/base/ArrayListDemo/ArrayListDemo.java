package com.base.ArrayListDemo;
/*
* 集合是一种容器，用来装数据的，类似与数组。-------- 容器操作【增删改查】
* ArrayList集合的底层原理是基于数组实现的
* 数组完成并启动后，长度就固定了
* 集合大小可变，开发中用的更多
* 集合种类很多，例如
* HashSet/List/Map/Set/TreeSet/ArrayList/HashMap/LinkedHashMap/LinkedHashSet/LinkedList/TreeMap
* 注意
* 集合和泛型都不支持基本数据类型，只能支持引用数据类型。
* 集合遍历元素删除，注意i++之后要在所删除元素内i--,或者倒着遍历i--
* */
import java.util.ArrayList;

public class ArrayListDemo {
    public static void main(String[] args) {
//        ArrayList list1 = new ArrayList();
        // 指定类型
        ArrayList<String> list = new ArrayList<>();
        // add
        list.add("demo1");
        list.add("demo2");
        list.add(1,"demo3");
        System.out.println(list); // [demo1, demo3, demo2]
        // get
        System.out.println(list.get(1)); // demo3
        // set
        list.set(1,"demo6");
        System.out.println(list.get(1)); // demo6
        // remove
        list.remove(2);
        System.out.println(list); // [demo1, demo6]
        // 集合个数
        System.out.println(list.size()); // 2

        ArrayList<String> list2 = new ArrayList<>();
        list2.add("demo1");
        list2.add("demo2");
        list2.add("test1");
        list2.add("demo3");
        list2.add("test2");

        // 第一种
//        for (int i = 0; i < list2.size(); i++) {
//            if(list2.get(i).contains("demo")){
//                list2.remove(i);
//                i--; // 重点
//            }
//        }
//        System.out.println(list2); // 错误 [demo2, test1, test2] 正确[test1, test2] 没有i--,会漏删元素

        // 第二种
        for (int i = list2.size() - 1; i >= 0; i--) {
            if(list2.get(i).contains("demo")){
                list2.remove(i);
            }
        }
        System.out.println(list2); // [test1, test2]

        ArrayListOperator ao = new ArrayListOperator();
        ao.add("demo1","demo1-dec1");
        ao.add("demo2","demo1-dec2");
        ao.get();
    }
}
