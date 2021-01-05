package com.code.basic.DynamicProxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author yan.kefei
 * @date 2018/5/20 22:41
 */
public class CglibProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("++++++before " + methodProxy.getSuperName() + " ++++++");
        System.out.println("Proxy method name: " + method.getName());
        Object obj = methodProxy.invokeSuper(o, objects);
        System.out.println("++++++after " + methodProxy.getSuperName() + " ++++++");
        return obj;
    }
}
