package com.code.demo.Java_Reflect;

import java.util.Arrays;
import java.util.List;

/**
 * @author yan.kefei
 * @date 2018/6/20 18:11
 */
@MyAnnotation("test myAnnotation")
public class MyClass {

    public String publicField;

    private String privateField;

    public List<String> list = Arrays.asList(new String[]{"item1", "item2"});

    public List<String> getStringList() {
        return list;
    }

    public MyClass(String publicField, String privateField) {
        this.publicField = publicField;
        this.privateField = privateField;
    }

    public void print() {
        System.out.println("公有方法调用：" + this.publicField + ", " + this.privateField);
    }

    private void print1() {
        System.out.println("私有方法调用：" + this.publicField + ", " + this.privateField);
    }

    public static void print2(String arg0) {
        System.out.println("静态方法调用：" + arg0);
    }

}
