package com.code.demo.DynamicProxy;

import net.sf.cglib.proxy.Enhancer;

/**
 * @author yan.kefei
 * @date 2018/5/20 22:47
 */
public class Demo2 {

    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TargetImpl.class);
        enhancer.setCallback(cglibProxy);

        Target o = (Target) enhancer.create();
        o.execute();
    }
}