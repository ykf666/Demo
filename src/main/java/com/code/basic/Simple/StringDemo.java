package com.code.basic.Simple;

/**
 * @author yan.kefei
 * @date 2017/9/22 13:51
 */
public class StringDemo {

    public static void main(String[] args) {
        String a = "love";
        String b = "love";
        String c = new String("love");

        System.out.println(a == b);//true
        System.out.println(a.equals(b));//true

        System.out.println(a == c);//false
        System.out.println(a.equals(c));//true

        Short data = null;
        getValue(data);
    }

    public static void getValue(short key) {
        short value = 1;
        if (value == key) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
    }
}
