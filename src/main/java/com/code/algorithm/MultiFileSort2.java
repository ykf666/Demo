package com.code.algorithm;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.*;

/**
 * 文件内保存的是long型数值的二进制
 * 多个已排序的子文件，合并输出到主文件内
 * <p>
 * 解题思想：
 * 每次读取各个文件的一个数值，比较出最小值追加写入到主文件
 * <p>
 * 优化：
 * 1、子文件输入流保存在fisArray数组中，无需每次读取数值打开文件
 * 2、每次遍历只读取一个文件的一个数值，子文件输出流维持了当前读取位置
 * 3、遍历比较数值大小后，仅记录下次需要读取的文件流在fisArray中的位置即可
 * <p>
 * Created by yankefei on 2022/4/16.
 */
public class MultiFileSort2 {

    /**
     * 循环读取所有文件，找到最小值写入
     *
     * @param smallSortedFiles
     * @param outputFile
     */
    public static void sort(String[] smallSortedFiles, String outputFile) {
        int subFileCount = smallSortedFiles.length;
        FileInputStream[] fisArray = new FileInputStream[subFileCount];
        for (int i = 0; i < subFileCount; i++) {
            String filePath = smallSortedFiles[i];
            File subFile = new File(filePath);
            if (!subFile.exists()) {
                //如果文件不存在，默认为null
                continue;
            }
            try {
                fisArray[i] = new FileInputStream(filePath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        //数据读取缓冲，long型占8字节
        byte[] bytes = new byte[8];
        Long[] tempArray = new Long[subFileCount];
        int position = -1;
        while (true) {
            for (int i = 0; i < subFileCount; i++) {
                //如果不是第一次读取或者不需要读取时，进入下个遍历元素
                if (position != i && position != -1) {
                    continue;
                }
                FileInputStream fis = fisArray[i];
                if (fis == null) {
                    continue;
                }
                try {
                    int count = fis.read(bytes);
                    if (count != -1) {
                        //读取到数值后，替换tempArray对应位置的值
                        Long targetVal = bytes2Long(bytes);
                        tempArray[i] = targetVal;
                    } else {
                        //文件读取完毕，把临时数组值置空，并且关闭文件流
                        tempArray[i] = null;
                        fis.close();
                        fis = null;
                        fisArray[i] = fis;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            position = subSort(tempArray);
            if (position == -1) {
                //合并排序结束
                break;
            } else {
                //写入最小值追加到主文件
                //此处也可优化为写入完毕后再关闭文件输出流，考虑到测试用例也用到这个write方法，暂不修改
                write(tempArray[position], outputFile);
            }
        }
        System.out.println("合并排序结束");
    }

    /**
     * 每次遍历比较获取最小值所在的数组位置
     * 数组中存在null元素
     * 如果所有元素为null，返回-1
     *
     * @return
     */
    private static int subSort(Long[] nums) {
        //如果遍历元素均为null，返回-1，以告诉调用者主循环结束
        int pos = -1;
        for (int i = 0; i < nums.length; i++) {
            Long num = nums[i];
            if (num == null) {
                continue;
            } else {
                if (pos == -1 || nums[pos] > num) {
                    pos = i;
                }
            }
        }
        return pos;
    }

    /**
     * 用于追加写入long型二进制
     *
     * @param value
     * @param filePath
     */
    public static void write(long value, String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("创建主文件" + filePath);
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        //追加写入
        try (FileOutputStream fos = new FileOutputStream(file, true)) {
            byte[] bytes = long2Bytes(value);
            fos.write(bytes);
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 字节数组转为long
     *
     * @param bytes
     * @return
     */
    public static Long bytes2Long(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.put(bytes, 0, bytes.length);
        buffer.flip();
        return buffer.getLong();
    }

    /**
     * long转为字节数组
     *
     * @param value
     * @return
     */
    public static byte[] long2Bytes(Long value) {
        return ByteBuffer.allocate(Long.SIZE / Byte.SIZE).putLong(value).array();
    }

    public static Long[] load(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return null;
        }
        List<Long> list = new ArrayList<>();
        byte[] bytes = new byte[8];
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            int count = 0;
            while (true) {
                count = fileInputStream.read(bytes);
                if (count == -1) {
                    break;
                }
                Long value = bytes2Long(bytes);
                list.add(value);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list.toArray(new Long[]{});
    }

}
