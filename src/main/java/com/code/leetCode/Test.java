package com.code.leetCode;

/**
 * Created by yankefei on 2020/12/11.
 */
public class Test {

    public static void main(String[] args) {
        int[] arr = {5, 5, 5, 10, 5, 5, 10, 20, 20, 20};
        System.out.println(zhaoling(arr));
    }

    private static boolean zhaoling(int[] bills) {
        if (bills == null || bills.length == 0) {
            return false;
        }
        int first = bills[0];
        if (first != 5) {
            return false;
        }
        int c5 = 1;
        int c10 = 0;
        int c20 = 0;
        for (int i = 1; i < bills.length; i++) {
            if (c5 * 5 + c10 * 10 + c20 * 20 < bills[i] - 5) {
                return false;
            }
            if (bills[i] == 5) {
                c5++;
            } else if (bills[i] == 10) {
                if (c5 < 1) {
                    return false;
                } else {
                    c5--;
                    c10++;
                }
            } else if (bills[i] == 20) {
                if (c10 > 0 && c5 > 0) {
                    return true;
                } else if (c5 > 2) {
                    return true;
                }
            } else {
                return false;
            }
        }
        return true;
    }

}
