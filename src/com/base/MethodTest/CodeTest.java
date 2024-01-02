package com.base.MethodTest;

import java.util.Random;
/*
* 随机验证码
* 定义一个for循环
* 随机生成0|1|2的数据，分别代表数字，大写字母，小写字母
* 把0|1|2交给switch生成对应类型的随机字符
* */
public class CodeTest {
    public static void main(String[] args) {
        String code = createCode(5);
        System.out.println(code);
    }

    public static String createcode1(int n){
        // 随机验证码
        String str = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0123456789";
        String code = "";
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            int index = r.nextInt(str.length());
            code += str.charAt(index);
        }
        return code;
    }

    public static String createCode(int n){
        Random r = new Random();
        String code = "";
        for (int i = 1;i<=n;i++){
            int type = r.nextInt(3); // 0 1 2
            switch (type){
                case 0:
                    code += r.nextInt(10); // 0 - 9
                    break;
                case 1:
                    // 大写 A:65 Z:65+25 (0-25) + 65
                    char dx = (char) (r.nextInt(26) + 65);
                    code += dx;
                    break;
                case 2:
                    // 小写 a:97 z:97+25 (0-25) + 97
                    char xx = (char) (r.nextInt(26) + 97);
                    code += xx;
                    break;
            }
        }
        return code;
    }

}
