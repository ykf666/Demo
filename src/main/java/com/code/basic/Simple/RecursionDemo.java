package com.code.basic.Simple;

/**
 * Created by yankefei on 2018/10/26.
 */
public class RecursionDemo {

    public static void main(String[] args) {
        System.out.println(factorial(5));
        System.out.println(fibonacci(12));
    }

    // 递归求阶乘
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    //一般而言，兔子在出生两个月后，就有繁殖能力，一对兔子每个月能生出一对小兔子来。
    // 如果所有兔子都不死，那么一年以后可以繁殖多少对兔子？
    public static int fibonacci(int m) {
        if (m <= 2) {
            return 1;
        } else {
            return fibonacci(m - 1) + fibonacci(m - 2);
        }
    }

}
