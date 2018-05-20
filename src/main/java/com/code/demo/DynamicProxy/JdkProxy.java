package com.code.demo.DynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author yan.kefei
 * @date 2018/5/17 17:43
 */
public class JdkProxy implements InvocationHandler {

    private Object target;

    JdkProxy(Object target) {
        super();
        this.target = target;
    }

    //在执行目标对象方法前，加上逻辑
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("++++++ before method ++++++");
        return method.invoke(target, args);
    }
}
