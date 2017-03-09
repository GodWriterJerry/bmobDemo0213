package com.example.yls.bmobdemo;

import cn.bmob.v3.BmobObject;

/**
 * Created by yls on 2017/3/6.
 */

public class Person extends BmobObject {
    private String name;
    private int age;
    private String address;

    public Person(){

    }

    public Person(String address, int age, String name) {
        this.address = address;
        this.age = age;
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
