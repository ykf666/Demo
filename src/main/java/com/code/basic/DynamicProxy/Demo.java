package com.code.basic.DynamicProxy;

import java.lang.reflect.Proxy;

/**
 * @author yan.kefei
 * @date 2018/5/17 17:50
 */
public class Demo {

    /**
     * <1>.JDK动态代理只能对实现了接口的类生成代理，而不能针对类
     * <2>.CGLIB是针对类实现代理，主要是对指定的类生成一个子类，覆盖其中的方法
     */
    public static void main(String[] args) {
        Target target = new TargetImpl();
        target.execute();

        Target targetProxy = (Target) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), new JdkProxy(target));
        targetProxy.execute();
    }

}
