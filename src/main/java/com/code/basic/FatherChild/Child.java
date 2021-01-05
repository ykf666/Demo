package com.code.basic.FatherChild;

/**
 * Created by yankefei on 2020/2/10.
 */
public class Child extends Father {

    private int var_private = 20;

    static {
        System.out.println("字类静态代码块");
    }

    public Child() {
        System.out.println("子类构造方法");
    }

    private void print_private() {
        System.out.println("字类私有方法: {var_private=" + this.var_private + ", var_public=" + var_public +
                ", var_friend=" + var_friend + ", var_protected=" + var_protected);
    }

    public static void main(String[] args) {
        Child child = new Child();
        child.var_private = 30;
        child.var_public = 30;
        child.print();
        child.print_private();
        child.print_protected();
        child.print_public();
    }
}
