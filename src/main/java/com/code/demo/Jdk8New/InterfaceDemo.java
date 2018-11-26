package com.code.demo.Jdk8New;

/**
 * Created by yankefei on 2018/11/26.
 */
public class InterfaceDemo implements NewInterface, NewInterface2 {

    public static void main(String[] args) {
        InterfaceDemo demo = new InterfaceDemo();
        demo.defaultMethod();
        demo.commonMethod();
        NewInterface.staticMethod();
    }

    @Override
    public void defaultMethod() {
        NewInterface2.super.defaultMethod();
    }

    @Override
    public void commonMethod() {
        System.out.println("InterfaceDemo common method");
    }

}
