package com.code.demo.simple;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author yan.kefei
 * @date 2018/6/7 17:21
 */
public class ComparatorDemo {

    public static void main(String[] args) {
        /**
         * 例1：实现Comparable的类（Person类），可以直接使用Collections.sort()方法排序，类本身已经实现了compareTo()方法
         */
        Person p1 = new Person("jack", 18);
        Person p2 = new Person("tom", 30);
        Person p3 = new Person("lucy", 45);
        Person p4 = new Person("ryan", 24);
        List<Person> list = Arrays.asList(p1, p2, p3, p4);
        Collections.sort(list);
        System.out.println(list);

        /**
         * 例2：Person2是一个没有实现Comparable接口的类，并且使用了final修饰类无法继承
         * 	   需要使用策略模式，实现Comparator类重写compare方法，达到排序的目的
         */
        Person2 p5 = new Person2("jack", 62);
        Person2 p6 = new Person2("tom", 27);
        Person2 p7 = new Person2("lucy", 19);
        Person2 p8 = new Person2("ryan", 60);
        List<Person2> list2 = Arrays.asList(p5, p6, p7, p8);
        list2.sort(new Comparator<Person2>() {
            @Override
            public int compare(Person2 o1, Person2 o2) {
                int a = o1.getAge() - o2.getAge();
                if (a < 0) {
                    return -1;
                } else {
                    return a == 0 ? 0 : 1;
                }
            }
        });
        System.out.println(list2);
    }
}
