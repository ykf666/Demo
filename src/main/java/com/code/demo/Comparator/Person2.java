package com.code.demo.Comparator;

/**
 * Created by yankefei on 2018/9/3.
 */
public final class Person2 {

    /**
     * Person2是一个没有实现Comparable接口的类，
     * 并且使用了final修饰类无法继承，需要使用策略模式，实现Comparator类重写compare方法，达到排序的目的
     */
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
