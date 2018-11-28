package com.code.stream;

import java.util.stream.Stream;

/**
 * Created by yankefei on 2018/11/28.
 * Stream的中间操作，有状态的操作和无状态的操作
 * 无状态的操作：map/mapToXXX  flatMap/flatMapXXX  filter  peek    unordered
 * 有状态的操作：distinct  sorted  limit/skip
 */
public class MiddleOperateDemo {

    public static void main(String[] args) {
        String str = "my name is jack";
        Stream.of(str.split("" )).map(s -> s.length()).forEach(System.out::println);

    }
}
