package com.code.lambda;

import java.util.function.Consumer;

/**
 * Created by yankefei on 2018/11/28.
 * 变量引用
 */
public class VarDemo {
    public static void main(String[] args) {
        //str是final的，jdk8默认可以不写了
        String str = "是中国人";
        Consumer<String> consumer = s -> System.out.println(s + str);
        consumer.accept("我");
    }
}
