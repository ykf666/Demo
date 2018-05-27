package com.code.demo.springAOP;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author yan.kefei
 * @date 2018/5/27 23:45
 */
public class TestAOP {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        SayService sayServiceA = context.getBean("sayServiceA", SayService.class);
        sayServiceA.say();
        System.out.println(sayServiceA.getClass());

        SayServiceImpl_B sayServiceB = context.getBean("sayServiceB", SayServiceImpl_B.class);
        sayServiceB.say();
        System.out.println(sayServiceB.getClass());
    }
}
