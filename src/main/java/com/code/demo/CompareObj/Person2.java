package com.code.demo.CompareObj;

/**
 * Created by yankefei on 2018/9/3.
 */
public class Person2 {

    private String name;

    private int age;

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

    @Override
    public String toString() {
        return this.getName() + ":" + this.getAge();
    }

}
