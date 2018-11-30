package com.code.demo.Simple;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by yankefei on 2018/11/30.
 * <p>
 * try-resource其实是一种语法糖，实际仍然是try-catch-finally的写法
 */
public class TryResourceDemo {

    public static void main(String[] args) {

        try (FileInputStream fio = new FileInputStream(new File("D:\\try.txt"))) {
            byte[] b = new byte[256];
            //每次读取一个字节，存放在i中
            int i = 0;
            //记录字节流的变量
            int index = 0;
            while ((i = fio.read()) != -1) {
                b[index] = (byte) i;
                index++;
            }
            System.out.println(new String(b));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
