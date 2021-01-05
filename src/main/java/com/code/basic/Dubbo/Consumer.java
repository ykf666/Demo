package com.code.basic.Dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yan.kefei
 * @date 2018/6/25 17:52
 */
public class Consumer {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("consumer.xml");
        context.start();
        System.out.println("consumer start");
        DemoService demoService = context.getBean(DemoService.class);
        System.out.println(demoService.getName(1));
        System.out.println(demoService.getName(11));
        DemoService demoService2 = context.getBean(DemoService.class);
        System.out.println(demoService2.getName(2));
        System.out.println(demoService2.getName(22));
    }
}
