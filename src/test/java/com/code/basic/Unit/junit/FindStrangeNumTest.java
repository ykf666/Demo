package com.code.basic.Unit.junit;

import com.code.algorithm.FindStrangeNum;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by yankefei on 2022/4/8.
 */
public class FindStrangeNumTest {

    @Test
    public void test1() {
        int[] arr = new int[]{3, 5, 2, 1, 4, 6};
        int expect = 6;
        int actual = FindStrangeNum.findStrangeNum(arr);
        Assert.assertEquals(expect, actual);
    }

    @Test
    public void test2() {
        int[] arr = new int[]{1, 3, 5, 2, 1, 4};
        int expect = 1;
        int actual = FindStrangeNum.findStrangeNum(arr);
        Assert.assertEquals(expect, actual);
    }

    @Test
    public void test3() {
        int[] arr = new int[]{3, 5, 2, -3, 1, 4};
        int expect = -3;
        int actual = FindStrangeNum.findStrangeNum(arr);
        Assert.assertEquals(expect, actual);
    }

}
