package com.code.basic.FatherChild;

/**
 * Created by yankefei on 2020/2/8.
 */
public class Father {

    static {
        System.out.println("父类静态代码块");
    }

    public Father() {
        System.out.println("父类构造方法");
    }

    private int var_private = 10;

    public int var_public = 10;

    int var_friend = 10;

    protected int var_protected = 10;

    //public 所有类可访问
    public void print_public() {
        System.out.println("父类public方法: {var_private=" + this.var_private + ", var_public=" + var_public +
                ", var_friend=" + var_friend + ", var_protected=" + var_protected);
    }

    //private 只有类内部可以访问
    private void print_private() {
        System.out.println("父类private方法: {var_private=" + this.var_private + ", var_public=" + var_public +
                ", var_friend=" + var_friend + ", var_protected=" + var_protected);
    }

    //默认 只有同一package内的类可以访问（不同包下的子类不能访问）
    void print() {
        System.out.println("父类方法: {var_private=" + this.var_private + ", var_public=" + var_public +
                ", var_friend=" + var_friend + ", var_protected=" + var_protected);
    }

    //protected 同一package和所有字类可以访问
    protected void print_protected() {
        System.out.println("父类protected方法: {var_private=" + this.var_private + ", var_public=" + var_public +
                ", var_friend=" + var_friend + ", var_protected=" + var_protected);
    }


}
