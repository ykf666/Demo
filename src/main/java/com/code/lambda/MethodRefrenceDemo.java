package com.code.lambda;

import java.util.function.*;

/**
 * Created by yankefei on 2018/11/28.
 * 方法引用
 */
public class MethodRefrenceDemo {

    public static void main(String[] args) {
        //方法引用
        Consumer<String> consumer = System.out::println;
        consumer.accept("方法引用");

        //静态方法引用
        Consumer<Dog> consumer1 = Dog::bark;
        consumer1.accept(new Dog());

        //非静态方法引用
        Dog dog = new Dog();
        IntUnaryOperator iuo = dog::eat;
        System.out.println(dog + "还剩" + iuo.applyAsInt(2) + "斤狗粮！");

        //使用类名的方法引用
        BiFunction<Dog, Integer, Integer> biFunction = Dog::eat;
        System.out.println(dog + "还剩" + biFunction.apply(dog, 4) + "斤狗粮！");

        //构造方法引用
        Supplier<Dog> supplier = Dog::new;
        System.out.println(supplier.get());

        //带参数的构造方法引用
        Function<String, Dog> function = Dog::new;
        System.out.println(function.apply("旺财"));

    }
}

class Dog {

    private String name = "丹尼";
    private int food = 20;

    public Dog() {
    }

    public Dog(String name) {
        this.name = name;
    }

    public static void bark(Dog dog) {
        System.out.println(dog + "叫了！");
    }

    //JDK会默认把当前示例this，作为参数传递给成员方法，第一个位置
    public int eat(int i) {
        System.out.println(this + "吃了" + i + "斤狗粮！");
        this.food -= i;
        return this.food;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
