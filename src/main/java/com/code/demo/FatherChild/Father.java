package com.code.demo.FatherChild;

/**
 * Created by yankefei on 2020/2/8.
 */
public class Father {

    static {
        System.out.println("Father static code block...");
    }

    public Father() {
        System.out.println("Father init");
    }

    private int var_private = 10;

    public int var_public = 10;

    int var_friend = 10;

    protected int var_protected = 10;

    public void print_public() {
        System.out.println("this is father public print: {var_private=" + this.var_private + ", var_public=" + var_public +
                ", var_friend=" + var_friend + ", var_protected=" + var_protected);
    }

    private void print_private() {
        System.out.println("this is father privae print: {var_private=" + this.var_private + ", var_public=" + var_public +
                ", var_friend=" + var_friend + ", var_protected=" + var_protected);
    }

    void print() {
        System.out.println("this is father print: {var_private=" + this.var_private + ", var_public=" + var_public +
                ", var_friend=" + var_friend + ", var_protected=" + var_protected);
    }

    protected void print_protected() {
        System.out.println("this is father protected print: {var_private=" + this.var_private + ", var_public=" + var_public +
                ", var_friend=" + var_friend + ", var_protected=" + var_protected);
    }



}
