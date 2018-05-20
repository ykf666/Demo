package com.code.demo.DynamicProxy;

import java.lang.reflect.Proxy;

/**
 * @author yan.kefei
 * @date 2018/5/17 17:50
 */
public class Demo {

    public static void main(String[] args) {
        Target target = new TargetImpl();
        target.execute();

        Target targetProxy = (Target) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), new JdkProxy(target));
        targetProxy.execute();
    }

}
