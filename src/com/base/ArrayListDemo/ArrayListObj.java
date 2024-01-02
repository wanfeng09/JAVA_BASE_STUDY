package com.base.ArrayListDemo;

public class ArrayListObj {
    private String name;
    private String dec;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDec() {
        return dec;
    }

    public void setDec(String dec) {
        this.dec = dec;
    }

    public ArrayListObj() {
    }

    public ArrayListObj(String name, String dec) {
        this.name = name;
        this.dec = dec;
    }
}
