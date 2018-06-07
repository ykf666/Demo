package com.code.demo.simple;

/**
 * @author yan.kefei
 * @date 2018/6/7 17:22
 */
public class Person implements Comparable<Person> {

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

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
    public int compareTo(Person o) {
        int a = this.age - o.age;
        if (a < 0) {
            return -1;
        } else {
            return a == 0 ? 0 : 1;
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
