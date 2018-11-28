package com.code.stream;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by yankefei on 2018/11/28.
 * Stream的中间操作，有状态的操作和无状态的操作
 * 无状态的操作：map/mapToXXX  flatMap/flatMapXXX  filter  peek    unordered
 * 有状态的操作：distinct  sorted  limit/skip
 */
public class IntermediateOperationDemo {

    public static void main(String[] args) {
        String str = "my name is tom";
        //把每个单词长度打印
        Stream.of(str.split(" ")).filter(s -> s.length() > 2).map(s -> s.length()).forEach(System.out::println);

        //flatMap A->B属性（是个集合，最终得到所有A元素里面的所有B属性集合）
        //intStream/longStream并不是Stream的子类，所以要进行装箱操作boxed
        Stream.of(str.split(" ")).flatMap(s -> s.chars().boxed()).forEach(integer -> System.out.println((char) integer.intValue()));

        //peek用于debug，是个中间操作，而forEach是终止操作
        System.out.println("---------------peek-------------");
        Stream.of(str.split(" ")).peek(System.out::println).forEach(System.out::println);

        //limit使用，主要用于无限流
        System.out.println("---------------limit-------------");
        new Random().ints().filter(i -> i > 100 && i < 1000).limit(10).forEach(System.out::println);

        //unordered操作不会进行任何显式的打乱流的操作。它的工作是：消除流中必须保持的有序约束，因此允许之后的操作使用，不必考虑有序的优化
        System.out.println("---------------unordered-------------");
        Stream.of(1, 2, 3, 4, 5).unordered().forEach(System.out::println);

        System.out.println("---------------distinct-------------");
        IntStream.of(2,12,67,44,2,12).distinct().forEach(System.out::println);

        System.out.println("---------------sorted-------------");
        IntStream.of(2,67,44,12).sorted().forEach(System.out::println);
    }
}
