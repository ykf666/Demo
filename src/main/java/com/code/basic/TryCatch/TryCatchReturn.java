package com.code.basic.TryCatch;

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
            //先执行（++i）
            return "a" + (++i);
        } catch (Exception e) {
            //如果有异常，执行此处的（++i）
            return "b" + (++i);
        } finally {
            //无论是否有异常，都会执行此处的（++i）
            //直接return
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
