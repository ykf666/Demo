package com.code.demo.DynamicProxy;

/**
 * @author yan.kefei
 * @date 2018/5/17 17:30
 */
public class TargetImpl implements Target {

    @Override
    public void execute() {
        System.out.println("target execute!");
    }
}
