package com.base.StringDemo;

import java.util.Scanner;

public class UserLogin {
    public static void main(String[] args) {
        for (int i = 0;i<3;i++) {
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入你的用户名");

            String username = sc.next();
            System.out.println("请输入你的密码");
            String pass = sc.next();

            boolean bol = login(username,pass);
            if(bol){
                System.out.println("登录成功");
                break;
            }else{
                System.out.println("登录失败");
            }
        }
    }
    public static boolean login(String name,String pass){ // 堆内存
        String oname = "admin"; // 常量池
        String opass = "123456";

        return oname.equals(name) && opass.equals(pass); // 字符串内容比较不建议 == 号
    }
}
