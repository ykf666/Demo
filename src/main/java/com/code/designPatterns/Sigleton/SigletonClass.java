package com.code.designPatterns.Sigleton;

/**
 * Created by yankefei on 2020/2/14.
 */
public class SigletonClass {

    public static SigletonClass INSTANCE = new SigletonClass();

    private volatile static SigletonClass instance = null;

    private Long a = 10L;
    private Long b = 20L;
    private Long c = 30L;

    private SigletonClass() {
    }

    public Long getA() {
        return this.a;
    }

    public Long getB() {
        return this.b;
    }

    public Long getC() {
        return this.c;
    }

    //double check locking
    public static SigletonClass getInstance() {
        if (instance == null) {
            synchronized (SigletonClass.class) {
                if (instance == null) {
                    instance = new SigletonClass();
                }
            }
        }
        return instance;
    }

}
