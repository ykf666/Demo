package com.code.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * 找出异常数字
 * 给定一个长度为N的数组，数组的值为1，2，3....N-1，另外一个数字可以为1到N-1之间，也可以是之外的值，找出这个数值
 * Created by yankefei on 2022/4/8.
 */
public class FindStrangeNum {

    public static int findStrangeNum(int[] numbers) {
        Set<Integer> intSet = new HashSet<>();
        for (int i = 0; i < numbers.length; i++) {
            if (intSet.contains(numbers[i])) {
                return numbers[i];
            } else {
                intSet.add(numbers[i]);
            }
            if (numbers[i] < 1 || numbers[i] > numbers.length - 1) {
                return numbers[i];
            }
        }
        return 0;
    }

}
