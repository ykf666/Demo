package com.code.demo.DynamicProxy;

/**
 * @author yan.kefei
 * @date 2018/5/17 17:50
 */
public class ProxyDemo {

    public static void main(String[] args) {
        Target demo = new TargetImpl();
        demo.execute();

        demo =  (Target) TargetProxy.bind(demo);
        demo.execute();
    }

}
