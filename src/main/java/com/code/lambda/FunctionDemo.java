package com.code.lambda;

import java.util.function.IntConsumer;
import java.util.function.IntPredicate;

/**
 * Created by yankefei on 2018/11/28.
 * <p>
 * 介个核心函数式接口：
 * 名称            输入       输出       功能说明
 * Predicate<T>         T       boolean        断言
 * Consumer<T>          T           /       消费一个数据
 * Supplier<T>          /           T       生产一个数据
 * Function<T,R>        T           R       输入T，输出R
 * UnaryOperator<T>     T           T       一元函数，输入输出类型相同，继承自Function
 * BiFunction<T,U,R>    (T,U)       R       两个参数的函数（两元函数）
 * BinaryOperator<T>    (T,T)       T       两元函数，输入输出类型相同
 */
public class FunctionDemo {

    public static void main(String[] args) {
        //断言函数
        IntPredicate predicate = i -> i > 0;
        System.out.println(predicate.test(10));

        //消费者函数
        IntConsumer consumer = i -> System.out.println(i);
        consumer.accept(9);

    }
}
