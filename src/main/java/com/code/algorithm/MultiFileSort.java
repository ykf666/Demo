package com.code.algorithm;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件内保存的是long型数值的二进制
 * 多个已排序的子文件，合并输出到主文件内
 * <p>
 * 解题思想：
 * 每次读取各个文件的一个数值，比较出最小值写入主文件，并记录每个子文件待读取数值的位置，以便下次获取
 * 例如第一次遍历每个文件index=0的位置
 * file_0  3
 * file_1  5
 * file_2  10
 * <p>
 * 则比较取出3，写入主文件
 * file_0的index=1，file_1和file_2维持原位置，继续下一次遍历对比，依次类推
 * <p>
 * Created by yankefei on 2022/4/12.
 */
public class MultiFileSort {

    /**
     * 循环读取所有文件，找到最小值写入
     *
     * @param smallSortedFiles
     * @param outputFile
     */
    public static void sort(String[] smallSortedFiles, String outputFile) {
        Map<String, Integer> fileLineMap = new HashMap<>();
        //初始化各个子文件读取的位置，用于记录每次遍历需要比较的子文件内数值位置
        for (String subFilePath : smallSortedFiles) {
            fileLineMap.put(subFilePath, 0);
        }
        //数据读取缓冲，long型占8字节
        byte[] bytes = new byte[8];
        //临时保存各个子文件对应的被比较值
        Map<String, Long> tempMap = new HashMap<>();
        while (true) {
            for (String subFilePath : smallSortedFiles) {
                File subFile = new File(subFilePath);
                if (!subFile.exists()) {
                    continue;
                }
                //如果该map未包含子文件，说明该文件已全部排序过，跳过
                if (!fileLineMap.containsKey(subFilePath)) {
                    continue;
                }
                try (FileInputStream fis = new FileInputStream(subFile)) {
                    int count = 0;
                    int num = 0;
                    int targetIndex = fileLineMap.get(subFilePath);
                    while (count != -1 && num <= targetIndex) {
                        //每次读取8字节
                        count = fis.read(bytes);
                        if (count != -1) {
                            if (num == targetIndex) {
                                long targetVal = bytes2Long(bytes);
                                tempMap.put(subFilePath, targetVal);
                            }
                            num++;
                            //记录每个子文件遍历到的位置
                            fileLineMap.put(subFilePath, num);
                        } else {
                            //子文件已读取完时，如果num和map缓存的数值相等，说明该文件已经排序完成
                            //下次循环可以跳过
                            if (num == targetIndex) {
                                fileLineMap.remove(subFilePath);
                            }
                        }
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (tempMap.size() != 0) {
                //保存每次循环查到的最小值
                Map.Entry<String, Long> minEntry = subSort(tempMap);
                for (String filePath : fileLineMap.keySet()) {
                    if (!filePath.equals(minEntry.getKey())) {
                        //最小值存在的子文件，索引位置不变，其他文件索引位置回退1
                        int index = fileLineMap.get(filePath);
                        if (index > 0) {
                            index--;
                        }
                        fileLineMap.put(filePath, index);
                    }
                }
                //写入最小值追加到主文件
                write(minEntry.getValue(), outputFile);
                tempMap.clear();
            } else {
                break;
            }
        }
        //排序结束
        System.out.println("排序合并结束");
    }

    /**
     * 每次遍历比较各个子文件需要比较的数，获取最小值
     *
     * @param map
     * @return
     */
    private static Map.Entry<String, Long> subSort(Map<String, Long> map) {
        Map.Entry<String, Long> minEntry = null;
        for (Map.Entry<String, Long> entry : map.entrySet()) {
            if (minEntry == null) {
                minEntry = entry;
            } else {
                if (minEntry.getValue() > entry.getValue()) {
                    minEntry = entry;
                }
            }
        }
        return minEntry;
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
    public static long bytes2Long(byte[] bytes) {
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
