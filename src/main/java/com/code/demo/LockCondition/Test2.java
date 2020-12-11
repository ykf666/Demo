package com.code.demo.LockCondition;

import java.util.*;

/**
 * Created by yankefei on 2020/12/11.
 */
public class Test2 {

    public static void main(String[] args) {
        System.out.println(numberTargets("byx", "yxbbdyex"));
        int[][] arr = {{3, 1}, {0, 2}, {1, 2}, {2, 3}};
        System.out.println(Arrays.toString(findRoute(arr)));
    }

    private static int numberTargets(String target, String block) {
        char[] targetArray = target.toCharArray();
        int targetLen = target.length();
        int index = 0;
        int count = 0;
        for (char c : block.toCharArray()) {
            if (targetArray[index] == c) {
                index++;
            }
            if (index == targetLen) {
                count++;
                index = 0;
            }
        }
        return count;
    }

    private static int[] findRoute(int[][] tickets) {
        int[] result = new int[tickets.length];
        return result;
    }

}
