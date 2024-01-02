package com.base;
/*
* switch 表达式不支持double,float、long类型数据
* case只能是字面量不能是变量
* switch不要忘记写break，否则会出现穿透现象
* 穿透性可以解决什么问题，若代码块一样可以简化代码
* switch性能比if更好
* */
public class SwitchTest {
    public static void main(String[] args) {
        int a = 10;
        switch (a){
            case 1:
                System.out.println("测试1");
                break;
            case 2:
                System.out.println("测试2");
                break;
            default:
                System.out.println("无匹配数据");
        }

        // 穿透性
        String b = "10";
        switch (b){
            case "ab":
                System.out.println("测试1");
                break;
            case "11":
            case "12":
            case "10":
                System.out.println("测试2");
                break;
            default:
                System.out.println("无匹配数据");
        }
    }
}
