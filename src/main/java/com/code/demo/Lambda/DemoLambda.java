package com.code.demo.Lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DemoLambda {

    public static void main(String[] args) {
        /**
         * 例1：替换匿名类实现Runnable接口
         */
        new Thread(() -> System.out.println("In Java8, Lambda expression rocks !!")).start();

        /**
         * 例2：假设String是一个没有实现Comparable接口的类，并且使用了final修饰类无法继承
         * 		需要使用策略模式，实现Comparator类重写compare方法，达到排序的目的
         * 	此处可使用lambda替代匿名类
         */
        List<String> list = new ArrayList<>();
        list.add("c");
        list.add("d");
        list.add("a");
        list.add("b");
        // lambda表达式实现比较器
        list.sort((final String o1, final String o2) -> o2.compareTo(o1));
        System.out.println(list);

        /**
         * 例3：对集合迭代
         */
        list.forEach(n -> System.out.println(n));
        list.forEach(System.out::println);

        /**
         * 例4：lambda表达式中加入Predicate
         */
        Predicate<String> p1 = n -> n.startsWith("J");
        Predicate<String> p2 = n -> n.length() > 3;
        List<String> names = Arrays.asList("Java", "Hello", "World", "JR");
        names.stream().filter(p1.and(p2)).forEach(System.out::println);

        /**
         * 例5：Map/Reduce
         */
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 500);
        costBeforeTax.stream().map((cost) -> cost + .12 * cost).forEach(System.out::println);
        double bill = costBeforeTax.stream().map((cost) -> cost + 0.12 * cost).reduce((sum, cost) -> sum + cost).get();
        System.out.println(bill);

        /**
         * 例6：通过filter创建一个String列表
         */
        List<String> list2 = Arrays.asList("abc", "bcd", "defg", "jk");
        List<String> list3 = list2.stream().filter(x -> x.length() > 3).collect(Collectors.toList());
        System.out.println(list3);

        /**
         * 例7：对集合元素应用函数，Map操作
         */
        List<String> list4 = list2.stream().map(x -> x.toUpperCase()).collect(Collectors.toList());
        System.out.println(list4);

        /**
         * 例8：复制不同的值，创建子列表（可理解为去重）
         */
        List<Integer> list5 = Arrays.asList(5, 13, 30, 66, 13, 5);
        List<Integer> list6 = list5.stream().map(n -> n).distinct().collect(Collectors.toList());
        System.out.println(list6);

        /**
         * 例9：计算集合的最大值，最小值，平均值，总和
         */
        IntSummaryStatistics iss = list5.stream().mapToInt(x -> x).summaryStatistics();
        System.out.println("平均值："+iss.getAverage());
        System.out.println("最小值："+iss.getMin());
        System.out.println("最大值："+iss.getMax());
        System.out.println("总和："+iss.getSum());
    }
}
