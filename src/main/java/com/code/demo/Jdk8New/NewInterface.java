package com.code.demo.Jdk8New;

/**
 * Created by yankefei on 2018/11/26.
 */
public interface NewInterface {

    static void staticMethod(){
        System.out.println("New Interface static method");
    }

    default void defaultMethod(){
        System.out.println("New Interface default method");
    }

    void commonMethod();
}
