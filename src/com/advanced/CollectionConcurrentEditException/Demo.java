package com.advanced.CollectionConcurrentEditException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/*
* 集合的并发修改异常
* 使用迭代器遍历集合时，又同时在删除集合中的数据，程序就会出现并发修改异常的数据
* 由于增强for循坏遍历集合就是迭代器遍历集合的简化写法，因此，使用增强for循环遍历集合，
* 又在同时删除几个中的数据时，程序也会出现并发修改异常的错误。
* 增强for循坏无法解决集合的并发修改异常。
*
* 如何保证遍历集合同时删除数据时不出现bug
* 使用迭代器遍历集合，但用迭代器自己的删除方法删除数据即可
* 如果能用for循环遍历时：可以倒着遍历并删除，或者从前往后遍历，但删除元素后做i--操作 // package com.base.ArrayListDemo;
*
* */
public class Demo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Collections.addAll(list,"aaa","nnn","ccc","aaa","ddd");
        System.out.println(list); // [aaa, nnn, ccc, aaa, ddd]

        // 删除所有包含a的元素
        Iterator<String> it = list.iterator();
        while (it.hasNext()){
            String name = it.next();
            if(name.contains("a")){
                // list.remove(name); // 报错 集合的并发修改异常 ConcurrentModificationException
                // 解决
                it.remove();
            }
        }
        System.out.println(list); // [nnn, ccc, ddd]
    }
}
