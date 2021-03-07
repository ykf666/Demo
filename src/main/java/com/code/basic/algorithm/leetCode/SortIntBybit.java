package com.code.basic.algorithm.leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by yankefei on 2020/12/2.
 * 根据整数二进制中包含1的个数排序，如果个数相同，按照大小排序
 */
public class SortIntBybit {

    public static void main(String[] args) {
        int[] array = {32, 11, 17, 190, 8, 226};
        System.out.println("排序前：" + Arrays.toString(array));
        List<Integer> list = new ArrayList<>();
        for (int i : array) {
            list.add(i);
            System.out.println(statisticsCountOne(i) + " " + getBits(i));
        }
        Collections.sort(list, (o1, o2) -> {
            int c = statisticsCountOne(o1) - statisticsCountOne(o2);
            if (c == 0) {
                return o1 - o2;
            } else {
                return c;
            }
        });
        System.out.print("排序后：");
        list.forEach((item) -> System.out.print(item + " "));
    }

    private static String getBits(int value) {
        return Integer.toBinaryString(value);
    }

    private static int statisticsCountOne(int value) {
        int count = 0;
        while (value != 0) {
            count += value % 2;
            value /= 2;
        }
        return count;
    }
}
