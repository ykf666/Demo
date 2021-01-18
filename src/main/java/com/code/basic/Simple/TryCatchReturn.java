package com.code.basic.Simple;

/**
 * Created by yankefei on 2021/1/15.
 */
public class TryCatchReturn {

    public static void main(String[] args) {
        TryCatchReturn demo = new TryCatchReturn();
        System.out.println(demo.exceptionReturn());
        System.out.println(demo.exceptionReturn2());
    }

    private String exceptionReturn() {
        int i = 1;
        try {
            return "a" + (++i);
        } catch (Exception e) {
            return "b" + (++i);
        } finally {
            return "c" + (++i);
        }
    }

    private int exceptionReturn2() {
        int i = 1;
        try {
            return i;
        } catch (Exception e) {
            return i;
        } finally {
            return ++i;
        }
    }
}
