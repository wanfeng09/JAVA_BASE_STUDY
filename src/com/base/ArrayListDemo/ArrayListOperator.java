package com.base.ArrayListDemo;

import java.util.ArrayList;

public class ArrayListOperator {
    ArrayList<ArrayListObj> list= new ArrayList<>();

    public void add(String name,String dec){
        ArrayListObj o = new ArrayListObj();
        o.setName(name);
        o.setDec(dec);
        list.add(o);
    }

    public void get(){
        System.out.println("---------------"+list.size());
        for (int i = 0; i < list.size(); i++) {
            System.out.println("名字---" + list.get(i).getName());
        }
    }
}
