package com.code.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by yankefei on 2018/11/28.
 * 创建流
 */
public class CreateStreamDemo {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        //从集合创建
        list.stream();
        list.parallelStream();

        //从数组创建流
        Arrays.stream(new int[]{1, 2, 3, 4, 5});

        //创建数字流
        IntStream.of(1, 2, 3);
        IntStream.rangeClosed(1, 10);

        //使用random创建一个无限流
        new Random().ints().limit(10);

        //自己生产的流
        Random random = new Random();
        Stream.generate(() -> random.ints().limit(20));

    }

}
