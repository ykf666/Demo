package com.code.basic.Unit.junit;

import com.code.algorithm.sort.BubbleSort;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by yankefei on 2022/4/8.
 */
public class BubbleSortTest {

    @Test
    public void test1() {
        int[] arr = new int[]{6, 5, 4, 3, 2, 1};
        int[] expect = new int[]{1, 2, 3, 4, 5, 6};
        BubbleSort.sort1(arr);
        for (int i = 0; i < arr.length; i++) {
            Assert.assertEquals(expect[i], arr[i]);
        }
    }

    @Test
    public void test2() {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6};
        int[] expect = new int[]{1, 2, 3, 4, 5, 6};
        BubbleSort.sort1(arr);
        for (int i = 0; i < arr.length; i++) {
            Assert.assertEquals(expect[i], arr[i]);
        }
    }

    @Test
    public void test3() {
        int[] arr = new int[]{6, 5, 4, 1, 2, 3};
        int[] expect = new int[]{1, 2, 3, 4, 5, 6};
        BubbleSort.sort1(arr);
        for (int i = 0; i < arr.length; i++) {
            Assert.assertEquals(expect[i], arr[i]);
        }
    }
}
