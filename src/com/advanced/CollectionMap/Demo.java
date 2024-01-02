package com.advanced.CollectionMap;

import java.util.*;
import java.util.function.Consumer;

/*
* 集合种类：HashSet/List/Map/Set/TreeSet/ArrayList/HashMap/LinkedHashMap/LinkedHashSet/LinkedList/TreeMap
* 集合体系结构
* 单列集合Collection: 每个元素（数据只包含一个值）
* 双列集合Map：每个元素包含两个值（键值对）
*
* 单列集合Collection【接口】
* List系列集合【接口】: 添加的元素是有序、可重复、有索引 （ArrayList、LinkedList【实现类】）
* Set系列集合【接口】：添加的元素是无序的、不重复、无索引
* HashSet【实现类】: 无序的、不重复、无索引
* LinkedHashSet【实现类】: 有序、不重复、无索引
* TreeSet【实现类】: 按照大小默认升序排序、不重复、无索引
*
* Collection
* 是单列集合的祖宗，它规定的方法（功能）是全部单列集合都会继承的
* 常用方法
* add(E e)添加元素，添加成功返回true
* clear() 清空集合元素
* isEmpty() 判断集合是否为空，是空返回true
* size() 获取集合元素个数
* contains(Object obj) 判断集合是否包含某个元素
* remove(E e) 删除某个元素，删除成功返回true，过个元素相同则删除第一个
* toArray() 集合转数组，返回Object[]类型
* 集合转指定类型数组
* String[] arr = c.toArray(new String[c.size()])
* 把一个集合的数据导入到另一个集合中去
* c1.addAll(c2)
*
* 遍历方式：迭代器、增强for、Lambda表达式
* 迭代器：用来遍历集合的专用方式（数组没有迭代器），在java中迭代器的代表是Iterator
* Iterator() 返回集合中的迭代器对象，该迭代器对象默认指向当前集合的第一个元素
* 常用方法
* hasNext() 询问当前位置是否有元素存在，存在返回true，反之
* next() 获取当前位置的元素，并同时将迭代器对象指向下一个元素处。
*
* 增强for
* 格式 for(元素的数据类型 变量名：数组或者集合){}
*
* Lambda表达式
* forEach(Consumer<? super T> action),结合Lambda表达式遍历集合
*
* List系列集合：有序、可重复、有索引
* 常用方法
* add(E e)添加元素，添加成功返回true
* add(索引,E e) 在某个索引位置插入元素
* remove(索引) 根据索引删除元素，返回被删除的元素
* get(索引) 根据索引获取元素
* set(索引,E e) 根据索引修改指定元素，返回原数据
*
* 遍历方式：for循环（因为有索引）、迭代器、增强for、Lambda表达式
*
* ArrayList集合的底层原理是基于数组实现的
* 特点：查询快（基于索引），增删慢（增删数据会进行索引迁移）
* 底层原理
* 利用无参数构造器创建的集合，会在底层创建一个默认长度为0的数组
* 添加第一个元素时，底层会创建一个新的长度为10的数组
* 存满时，会扩容1.5倍
* 如果一次性添加过个元素，1.5倍还放不下，则会新创建数组的长度以实际为准
*
* 单向链表：只记录了下一个节点的地址
* 双向链表：记录了前后节点的地址【查询慢，增删相对较快，但对首尾进行增删的速度是极快的】
*
* LinkedList集合的底层原理是基于双链表实现的
* 链表中的节点是独立的对象，在内存中是不连续的，每个节点包包含数据值和下一个节点的地址【值：下一个节点地址】
* 特点
* 查询慢，无论查询那个数据都要从头开始找
* 链表增删相对快【只需要修改地址指向】
* 常用方法
* addFirst(E e) 列表开头添加元素 --- 等同于 push
* addLast(E e) 列表末尾追加元素
* getFirst() 获取第一个元素
* getLast() 获取最后一个元素
* removeFirst() 删除第一个元素，并返回该元素  ---  等同于 pop
* removeLast() 删除最后一个元素，并返回该元素
*
* 应用场景
* 设计队列【先进先出，后进后出】
* 设计栈【后进先出，先进后出】 压/进栈push  弹/出栈pop 【手枪弹夹】
*
* Set系列集合
* 常用方法：基本与Collection一致
* HashSet集合的底层原理基于哈希表实现的
* 哈希表是一种增删改查数据，性能都较好的数据结构
* 对象内容一样的，去重 --- 重写hasCode()和equals() 方法
*
* 哈希表
* jdk8之前：哈希表=数组+链表
* jdk8之后：哈希表=数组+链表+红黑树
*
* 底层原理
* 默认创建长度16的数组，默认加载因子为0.75，数组名table
* 使用元素的哈希值对数组长度求余计算出应存入的位置
* 判断当前位置是否为null,如果是null直接存入
* 如果不为null，表示有元素，则调用equals方法进行比较【相等：不存；不相等，则存入数组】
* jdk8之前: 新元素存入数组，占老元素位置，老元素挂下面
* jdk8之后: 新元素直接挂在老元素下面
*
* 链表过长，导致查询性能降低，如何解决？
* 扩容【默认加载因子为0.75】 16 * 0.75 = 12
*
* jdk8之后：当链表长度超过8，且数组长度>=64时,自动将链表装成红黑树（树结构），进一步提高了操作数据的性能
*
* 哈希值：int类型数值，java中每个对象都有一个哈希值
* Object类提供hashCode方法，返回该对象自己的哈希值
* 对象哈希值特点
* 同一个对象多次调用hashCode(),返回哈希值相同
* 不同对象，哈希值一般不相同，但也有可能会相同（哈希碰撞）【概率较低】
*
* 树结构：二叉树、二叉查找树（排序）、平衡二叉树、红黑树【自平衡的二叉树】
*
* LinkedHashSet底层原理
* 基于哈希表（数组、链表、红黑树）
* 双链表机制记录前后元素的位置【有序，缺点：内存占用过多】
*
* TreeSet底层原理: 按照大小默认升序排序、不重复、无索引
* 基于红黑树实现的排序，对于自定义类型，如对象，是无法直接排序的，会报错
* 自定义排序规则
* 让自定义类实现Comparable接口，重写里面的compareTo方法来指定比较规则。
* 通过调用TreeSet集合有参数构造器，可以设置Comparator对象（比较器对象，用来指定比较规则）
* 注意
* 如果类本身有实现Comparable接口，TreeSet集合同时也自带比较器，默认使用集合自带的比较器排序
*
*
* 总结
* ArrayList：记住元素的添加顺序，存重复元素，频繁根据索引查询数据
* LinkedList: 记住元素的添加顺序，且增删首尾数据的情况较多
* HashSet: 不在意元素顺序，无重复元素，增删改查快
* LinkerHashSet: 记住元素的添加顺序，无重复元素，增删改查快
* TreeSet: 对元素进行排序，无重复元素，增删改查快
*
*
* Map集合
* Map集合称为双列集合、键值对集合
* Map集合所有键是不允许重复的，但是值可以重复，键与值是一一对应的，每一个键只能找到自己对应的值
* 由键决定的特点
* HashMap：无序、不重复、无索引（常用）
* LinkedHashMap: 有序、不重复、无索引
* TreeMap: 按照大小默认升序排序、不重复、无索引
*
* Map是双列集合的祖宗，它的功能是全部双列集合都可以继承过来使用的
* 常用方法
* put(K key, V value) 添加元素
* size() 获取集合的大小
* clear() 清空集合
* isEmpty() 判断集合是否为空
* get(Object key) 根据键获取对应值
* remove(Object key) 根据键删除整个元素
* containsKey(Object key) 判断是否包含某个键
* containsValue(Object value) 判断是否包含某个值
* keySet() 获取全部键的集合
* values() 获取Map集合的全部值
* putAll() 把其他Map()集合数据导入到自己集合中来。
*
* Map集合的遍历方式
* 键找值: 先获取Map集合全部的键，在通过遍历键来找值
* 键值对: 把键值对看成一个整体进行遍历 entrySet()
* Lambda：Jdk8之后的新技术,forEach(BiConsumer<? super K,? super V> action) 结合lambda遍历Map集合
*
*
* */
public class Demo {
    public static void main(String[] args) {
        // 单列集合Collection
        ArrayList<String> al = new ArrayList<>(); // 有序、可重复、有索引
        al.add("java1");
        al.add("java3");
        al.add("java12");
        al.add("java1");
        System.out.println(al); // [java1, java3, java12, java1]
        System.out.println(al.get(3)); // java1

        HashSet<String> hs = new HashSet<>(); // 无序的、不重复、无索引
        hs.add("java1");
        hs.add("java3");
        hs.add("java12");
        hs.add("java1");
        System.out.println(hs); // [java3, java1, java12]

        System.out.println("======================");

        Collection<String> c1 = new ArrayList<>(); // 多态写法
        // 添加集合元素
        c1.add("java3");
        c1.add("java6");
        System.out.println(c1); // [java3, java6]

        // 清空集合元素
        // c1.clear();
        // System.out.println(c1); // []

        // 判断集合是否为空，是空返回true
        // System.out.println(c1.isEmpty()); // true
        // 获取集合元素个数
        // System.out.println(c1.size()); // 2
        // 判断集合是否包含某个元素
        // System.out.println(c1.contains("java3")); // true
        // 删除某个元素
        // c1.remove("java3"); // true
        // System.out.println(c1); // [java6]

        // 集合转数组
        Object[] oa = c1.toArray();
        System.out.println(Arrays.toString(oa)); // [java3, java6]

        // 集合转指定类型数组
        String[] sa = c1.toArray(new String[c1.size()]);
        System.out.println(Arrays.toString(sa)); // [java3, java6]

        Collection<String> c2 = new ArrayList<>(); // 多态写法
        c2.add("java7");
        c2.add("java8");

        // 把一个集合的数据导入到另一个集合中去
        c1.addAll(c2);
        System.out.println(c1); // [java3, java6, java7, java8]
        System.out.println(c2); // [java7, java8]


        System.out.println("----------------------------");

        // 迭代器 从集合对象获取迭代器对象
        Iterator<String> it = c1.iterator();
        while (it.hasNext()){
            String ele = it.next();
            System.out.println(ele);
        }

        System.out.println("--------------------------");

        // 增强for
        for (String ele:c2){
            System.out.println(ele);
        }
        System.out.println("--------------------------");

        // Lambda表达式
//        c2.forEach(new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//                System.out.println(s);
//            }
//        });
        // 简化
        // c2.forEach((String s) -> System.out.println(s));
        c2.forEach(System.out::println);

        List<String> l1 = new ArrayList<>(); // 多态写法

        System.out.println("------------------------");
        // 创建队列【先进先出】
        LinkedList<String> ld = new LinkedList<>();
        // 入队
        ld.addLast("队列1");
        ld.addLast("队列2");
        ld.addLast("队列3");
        System.out.println(ld); // [队列1, 队列2, 队列3]
        // 出队
        System.out.println(ld.removeFirst()); // 队列1
        System.out.println(ld.removeFirst()); // 队列2
        System.out.println(ld.removeFirst()); // 队列3
        System.out.println(ld); // []

        System.out.println("------------------------");

        // 设计栈【后进先出，先进后出】
        ld.push("弹夹1"); //  等同于ld.addFirst("弹夹1");
        ld.push("弹夹2");
        ld.push("弹夹3");
        System.out.println(ld); // [弹夹3, 弹夹2, 弹夹1]
        System.out.println(ld.pop()); // 弹夹3 // 等同于ld.removeFirst()
        System.out.println(ld.pop()); // 弹夹2
        System.out.println(ld.pop()); // 弹夹1


        System.out.println("----------------------------");
        // Set系列集合 无序的、不重复、无索引
        Set<String> set = new HashSet<>();
        set.add("AAA");
        set.add("888");
        set.add("CCC");
        set.add("BBB");
        set.add("BBB");
        set.add("111");
        System.out.println(set); // [AAA, 111, CCC, BBB, 888]

        System.out.println(set.hashCode()); // 300879
        System.out.println(c1.hashCode()); // 826978181
        System.out.println(c2.hashCode()); // -1066185118

        // 哈希碰撞
        String str1 = new String("abc");
        String str2 = new String("acD");
        System.out.println(str1.hashCode()); // 96354
        System.out.println(str2.hashCode()); // 96354


        System.out.println("-------------------");
        // 对象内容一样的，去重 --- 重写hasCode()和equals() 方法
        Set<Cat> cat = new HashSet<>();
        cat.add(new Cat("布偶",1,2000));
        cat.add(new Cat("布偶",1,2000));
        cat.add(new Cat("英短",2,999.99));
        System.out.println(cat); // [Cat{name='布偶', age=1, price=2000.0}, Cat{name='英短', age=2, price=999.99}]

        System.out.println("-------------------");

        Set<Cat> treeCat = new TreeSet<>(); // 按照大小默认升序排序、不重复、无索引,对象无法排序，需自定义
        treeCat.add(new Cat("布偶",1,1666.88));
        treeCat.add(new Cat("狸花猫",2,216.66));
        treeCat.add(new Cat("奶牛猫",2,66.66));
        treeCat.add(new Cat("布偶",1,1666.88));
        treeCat.add(new Cat("奶牛猫",2,66.66));
        //相同年龄会被去重
        System.out.println(treeCat); // [Cat{name='布偶', age=1, price=1666.88}, Cat{name='狸花猫', age=2, price=216.66}]

        // 就近原则
        /*Set<Cat> treeCat2 = new TreeSet<>(new Comparator<Cat>() {
            @Override
            public int compare(Cat o1, Cat o2) {
                return Double.compare(o1.getPrice(),o2.getPrice());
            }
        });*/
        // 简化
        Set<Cat> treeCat2 = new TreeSet<>((o1,o2) -> Double.compare(o1.getPrice(),o2.getPrice()));
        treeCat2.add(new Cat("狸花猫",2,216.66));
        treeCat2.add(new Cat("奶牛猫",2,66.66));
        treeCat2.add(new Cat("布偶",1,1666.88));
        treeCat2.add(new Cat("奶牛猫",2,66.66));
        // [Cat{name='奶牛猫', age=2, price=66.66}, Cat{name='狸花猫', age=2, price=216.66}, Cat{name='布偶', age=1, price=1666.88}]
        System.out.println(treeCat2);

        System.out.println("-----------------Map-------------------------");
        // 无序、不重复、无索引
        Map<String,Integer> map = new HashMap<>();
        map.put("A",100);
        map.put("B",200);
        map.put("C",300);
        map.put("D",100);
        map.put("D",100);
        System.out.println(map); // {A=100, B=200, C=300, D=100}

        // 按照大小默认升序排序、不重复、无索引
        Map<Integer,String> map1 = new TreeMap<>();
        map1.put(2,"狸花猫");
        map1.put(3,"奶牛猫");
        map1.put(1,"布偶");
        System.out.println(map1); // {1=布偶, 2=狸花猫, 3=奶牛猫}

        // size()
        // System.out.println(map1.size()); // 3
        // clear()
        // map1.clear();
        // System.out.println(map1); // {}
        // System.out.println(map1.isEmpty()); // true
        // System.out.println(map1.get(1)); // 布偶
        // System.out.println(map.get("A")); // 100
        // map1.remove(1);
        // System.out.println(map1); // {2=狸花猫, 3=奶牛猫}
        System.out.println(map1.containsKey(1)); // true
        System.out.println(map1.containsKey(6)); // false
        System.out.println(map1.containsValue("布偶")); // true

        // keySet() 获取Map集合的全部键
        Set<Integer> keys = map1.keySet();
        System.out.println(keys); // [1, 2, 3]

        // values() 获取Map集合的全部值
        Collection<String> value = map1.values();
        System.out.println(value); // [布偶, 狸花猫, 奶牛猫]

        // putAll() 把其他Map()集合数据导入到自己集合中来。
        System.out.println("---------------");
        System.out.println(map); // {A=100, B=200, C=300, D=100}
        System.out.println(map1); // {1=布偶, 2=狸花猫, 3=奶牛猫}

        Map<Integer,String> map2 = new HashMap<>();
        map2.put(6,"测试6");
        map2.put(7,"测试8");
        map2.put(8,"测试6");
        System.out.println(map2); // {6=测试6, 7=测试8, 8=测试6}
        map1.putAll(map2);
        System.out.println(map1); // {1=布偶, 2=狸花猫, 3=奶牛猫, 6=测试6, 7=测试8, 8=测试6}
        System.out.println(map2); // {6=测试6, 7=测试8, 8=测试6}

        System.out.println("---------------循环遍历-------------");

        // 键找值
        for (Integer key: keys){
            String ele = map1.get(key);
            System.out.println(key + "----" + ele);
        }

        System.out.println("------------------");

        // 键值对
        // 调用Map集合提供entrySet方法，把Map集合转换成键值对类型的Set集合
        Set<Map.Entry<Integer,String>> entries = map1.entrySet();
        System.out.println(entries); // [1=布偶, 2=狸花猫, 3=奶牛猫, 6=测试6, 7=测试8, 8=测试6]
        for (Map.Entry<Integer,String> ele: entries){
            Integer key2 = ele.getKey();
            String value2 = ele.getValue();
            System.out.println(key2 + "----" + value2);
        }

        System.out.println("--------------------------------------------");

        // Lambda jdk8新增
        map1.forEach((k,v) -> {
            System.out.println(k + "----" + v);
        });


    }
}


class Cat implements Comparable<Cat>{
    private String name;
    private int age;
    private double price;

    // TreeSet自定义排序比较规则
    @Override
    public int compareTo(Cat o) {
        return this.age - o.getAge();
    }

    // 重写hasCode()和equals() 方法
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

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", price=" + price +
                '}';
    }

    public Cat() {
    }

    public Cat(String name, int age, double price) {
        this.name = name;
        this.age = age;
        this.price = price;
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
