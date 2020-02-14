package com.code.demo.Simple;

/**
 * Created by yankefei on 2020/2/14.
 */
public class SigletonClass {

    private static SigletonClass INSTANCE = new SigletonClass();

    private SigletonClass() {
    }

    public static SigletonClass getInstance() {
        return INSTANCE;
    }
}
