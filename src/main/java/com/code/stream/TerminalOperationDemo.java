package com.code.stream;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by yankefei on 2018/11/29.
 * <p>
 * 终止操作：分为短路操作和非短路操作
 * <p>
 * 短路操作：不需要全部流数据都计算完，就可以结束操作
 * findFirst/findAny   allMatch    anyMatch    noneMatch
 * 非短路操作：全部数据计算完成，结束操作
 * forEach/forEachOrdered  collect/toArray reduce  min/max/count
 */
public class TerminalOperationDemo {

    public static void main(String[] args) {
        String str = "my name is tom";

        System.out.println("----------非短路操作-----------");
        //使用并行流
        str.chars().parallel().forEach(i -> System.out.print((char) i));

        System.out.println();
        //使用foreachOrdered保证顺序
        str.chars().parallel().forEachOrdered(i -> System.out.print((char) i));

        System.out.println();
        //收集到list
        List<String> list = Stream.of(str.split(" ")).collect(Collectors.toList());
        System.out.println(list);

        Optional optional = Stream.of(str.split(" ")).reduce((s1, s2) -> s1 + "|" + s2);
        System.out.println(optional.orElse(""));

        //带初始化值的reduce操作
        String result = Stream.of(str.split(" ")).reduce("", (s1, s2) -> s1 + "|" + s2);
        System.out.println(result);

        //字符串中单词的总长度
        int len = Stream.of(str.split(" ")).map(s -> s.length()).reduce(0, (l1, l2) -> l1 + l2);
        System.out.println("单词总长度为：" + len);

        //求最大值
        String max = Stream.of(str.split(" ")).max((s1, s2) -> (s1.length() - s2.length())).get();
        System.out.println("最长单词长度为：" + max);

        System.out.println("----------短路操作-----------");
        //findFirst
        int first = new Random().ints().findFirst().getAsInt();
        System.out.println("获取到第一个值为：" + first);
    }
}
