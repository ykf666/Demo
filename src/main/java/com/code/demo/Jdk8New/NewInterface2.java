package com.code.demo.Jdk8New;

/**
 * Created by yankefei on 2018/11/26.
 */
public interface NewInterface2 {

    default void defaultMethod() {
        System.out.println("New Interface2 default method");
    }

}
