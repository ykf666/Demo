package com.code.Genericity;

import java.util.ArrayList;
import java.util.List;

/**
 * producer extend consumer super
 * <p>
 * Created by yankefei on 2020/4/24.
 */
public class PecsMain {

    /**
     * Producer extends Consumer super  生产者使用extends，consumer使用super
     * 这里的生产者和消费者是相对容器而言的，
     * 生产者只能对外提供数据，不可以写入数据，数据来源于赋值操作（将参数化类型为子类的容器赋值过来）
     * 消费者表示只能向容器中写入数据，不能读取（只能以Object来接收）
     * 这里的extends和super指的是声明类型和参数化类型的关系,
     * 如下所示：等号左侧类型为声明类型，右侧为参数化类型
     * List<? extends Number> intList = new ArrayList<Integer>();
     * private List<? super Number> intList2 = new ArrayList<Number>();
     *
     * @param args
     */
    public static void main(String[] args) {
        List<? extends Number> list = new ArrayList<>();
//        list.add(2);//error
//        list.add(3l);//error
        Number number = list.get(0);
        System.out.println(number);


        List<? super Number> list2 = new ArrayList<>();
        list2.add(2);
        list2.add(3l);
        Object number1 = list2.get(0);
        System.out.println(number1);
    }
}
