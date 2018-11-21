package com.code.demo.SpringAOP;

import org.springframework.stereotype.Component;

/**
 * @author yan.kefei
 * @date 2018/5/27 23:35
 */
@Component("sayServiceB")
public class SayServiceImpl_B {

    public void say() {
        System.out.println("hello spring B");
    }
}
