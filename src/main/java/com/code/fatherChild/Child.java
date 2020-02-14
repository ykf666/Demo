package com.code.fatherChild;

/**
 * Created by yankefei on 2020/2/10.
 */
public class Child extends Father {

    private int var_private = 20;

    public Child() {
        System.out.println("Child init");
    }

    private void print_private() {
        System.out.println("this is child private print: {var_private=" + this.var_private + ", var_public=" + var_public +
                ", var_friend=" + var_friend + ", var_protected=" + var_protected);
    }

    public static void main(String[] args) {
        Child child = new Child();
        child.var_private = 30;
        child.var_public = 30;
        child.print();
        child.print_private();
    }
}
