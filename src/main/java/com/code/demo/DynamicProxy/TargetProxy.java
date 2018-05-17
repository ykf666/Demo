package com.code.demo.DynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author yan.kefei
 * @date 2018/5/17 17:43
 */
public class TargetProxy implements InvocationHandler {

    private Object target;

    private TargetProxy(Object target) {
        this.target = target;
    }

    //生成一个目标对象的代理对象
    public static Object bind(Object target) {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new TargetProxy(target));
    }

    //在执行目标对象方法前，加上判断逻辑
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy start!");
        return method.invoke(target, args);
    }
}
