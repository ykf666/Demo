package com.code.lambda;

/**
 * Created by yankefei on 2018/11/28.
 * 类型推断
 */
public class TypeDemo {
    public static void main(String[] args) {
        //变量类型定义
        IMath lambda = (x, y) -> x + y;

        //数组里
        IMath[] lambda1 = {(x, y) -> x + y};

        //强转
        Object lambda2 = (IMath) (x, y) -> x + y;

        //通过返回值类型
        IMath lambda3 = createLambda();

        //当方法重载时，存在二义性的函数传入，需要强转类型接口解决
        TypeDemo demo = new TypeDemo();
        demo.test((IMath) (x, y) -> x + y);
    }

    public void test(IMath iMath) {
    }

    public void test(IMath2 iMath) {
    }

    public static IMath createLambda() {
        return (x, y) -> x + y;
    }
}

@FunctionalInterface
interface IMath {
    int add(int x, int y);
}

@FunctionalInterface
interface IMath2 {
    int sub(int x, int y);
}
