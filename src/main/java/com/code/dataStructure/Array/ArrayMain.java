package com.code.dataStructure.Array;

import java.util.Random;

/**
 * Created by yankefei on 2020/2/3.
 */
public class ArrayMain {

    public static void main(String[] args) {
        //数组
        MyArray myArray = new MyArray();
        for (int i = 0; i < 11; i++) {
            myArray.addLast(new Random().nextInt(100));
        }
        System.out.println(myArray.toString());
        myArray.remove(0);
        System.out.println(myArray.toString());
        myArray.remove(0);
        myArray.remove(0);
        myArray.remove(0);
        myArray.remove(0);
        myArray.remove(0);
        System.out.println(myArray.toString());
    }
}
