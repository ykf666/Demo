package com.code.basic.Unit.junit;

import com.code.algorithm.ExtractNumbers;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by yankefei on 2022/4/12.
 */
public class ExtractNumbersTest {

    @Test
    public void test4() {
        String[] testStr4 = new String[]{"12.3"};
        String[] expect = new String[]{"12.3"};
        String[] actual = ExtractNumbers.extractNumbers(testStr4);
        Assert.assertArrayEquals(expect, actual);
    }

    @Test
    public void test5() {
        String[] testStr5 = new String[]{"hij123", "123.4kkk", "1a3n"};
        String[] expect = new String[]{};
        String[] actual = ExtractNumbers.extractNumbers(testStr5);
        Assert.assertArrayEquals(expect, actual);
    }

    @Test
    public void test6() {
        String[] testStr6 = new String[]{"12...3", "", "12.3"};
        String[] expect = new String[]{"12.3"};
        String[] actual = ExtractNumbers.extractNumbers(testStr6);
        Assert.assertArrayEquals(expect, actual);
    }

}
