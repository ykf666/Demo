package com.code.basic.DynamicProxy;

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

        TargetImpl o = (TargetImpl) enhancer.create();
        o.execute();
    }
}
