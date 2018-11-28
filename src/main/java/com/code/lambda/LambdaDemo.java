package com.code.lambda;

/**
 * Created by yankefei on 2018/11/27.
 */
public class LambdaDemo {
    public static void main(String[] args) {
        interface1 i1 = (i) -> i * 2;

        interface1 i2 = i -> i * 2; //最常见写法

        interface1 i3 = (int i) -> i * 2;

        interface1 i4 = i -> {
            return i * 2;
        };

        System.out.println(i2.doubleNum(4));
        System.out.println(i1.add(3, 7));

    }
}

@FunctionalInterface
interface interface1 {
    //注解表明，此接口仅有一个抽象方法且必须有，单一责任制
    //微服务思想跟这个挺像啊，尽量细粒度拆分接口
    int doubleNum(int i);

    //jdk8新特性，默认方法在实现此接口的类中，不是必须实现的
    default int add(int x, int y) {
        return x + y;
    }
}

@FunctionalInterface
interface interface2 {

    int doubleNum(int i);

    default int add(int x, int y) {
        return x + y;
    }
}

@FunctionalInterface
interface interface3 extends interface1, interface2{

    int doubleNum(int i);

    @Override
    default int add(int x, int y) {
        return interface1.super.add(x, y);
    }

}
