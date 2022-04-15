package com.code.basic.Unit.junit;

import com.code.algorithm.MultiFileSort;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by yankefei on 2022/4/12.
 */
public class MultiFileSortTest {

    private final static String[] SMALL_FILES = new String[]{"D:\\subFile_0", "D:\\subFile_1", "D:\\subFile_2"};

    private final static String MAIN_FILE_PATH = "D:\\mainFile";

    private final static List<Long> VALUE_LIST = new ArrayList<>();

    @Before
    public void before() {
        //初始化主文件
        File mainFile = new File(MAIN_FILE_PATH);
        mainFile.deleteOnExit();
        try {
            mainFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Random random = new Random();
        for (String filePath : SMALL_FILES) {
            File file = new File(filePath);
            file.deleteOnExit();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            int size = random.nextInt(6) + 5;
            long[] arr = new long[size];
            System.out.println("文件" + filePath + "写入数量：" + size);
            for (int i = 0; i < size; i++) {
                long value = new Random().nextInt(100);
                VALUE_LIST.add(value);
                arr[i] = value;
            }
            Arrays.sort(arr);
            //写入子文件
            for (long val : arr) {
                MultiFileSort.write(val, filePath);
            }
            System.out.println(Arrays.toString(arr));
        }
    }


    @Test
    public void testSort() {
        Long[] expect = VALUE_LIST.toArray(new Long[]{});
        Arrays.sort(expect);
        System.out.println(Arrays.toString(expect));
        MultiFileSort.sort(SMALL_FILES, MAIN_FILE_PATH);
        Long[] actual = MultiFileSort.load(MAIN_FILE_PATH);
        System.out.println(Arrays.toString(actual));
        Assert.assertArrayEquals(expect, actual);
    }
}
