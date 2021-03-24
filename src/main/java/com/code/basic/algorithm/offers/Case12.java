package com.code.basic.algorithm.offers;

/**
 * 回溯法
 * 二维数组存储字母，给定字符串，输出是否存在一条路径，连接数组中字符得到该字符串
 * 要求：每个字符只能连接一次
 * Created by yankefei on 2021/3/24.
 */
public class Case12 {

    public static void main(String[] args) {
        String target  = "bfce";
        char[] targetArr = target.toCharArray();
        char[][] arr = new char[][]{{'a', 'b', 't', 'g'}, {'c', 'f', 'c', 's'}, {'j', 'd', 'e', 'h'}};
        for (int i = 0; i < arr.length; i++) {
            char[] arr2 = arr[i];
            for (int j = 0; j < arr2.length; j++) {
                if (arr2[j]==targetArr[0]){
                    System.out.println(arr2[j]);
                    if (i==0){
                        System.out.println(arr[0][j-1]);
                        System.out.println(arr[0][j+1]);
                        System.out.println(arr[1][j]);
                    } else {
                        System.out.println(arr[i-1][j]);
                        System.out.println(arr[i+1][j]);
                        System.out.println(arr[i][j-1]);
                        System.out.println(arr[i][j+1]);
                    }
                }

            }
        }
    }
}
