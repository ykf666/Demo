package com.code.basic.SpringAOP;

import org.springframework.stereotype.Component;

/**
 * @author yan.kefei
 * @date 2018/5/27 23:35
 */
@Component("sayServiceA")
public class SayServiceImpl_A implements SayService {
    @Override
    public void say() {
        System.out.println("hello spring A");
    }
}
