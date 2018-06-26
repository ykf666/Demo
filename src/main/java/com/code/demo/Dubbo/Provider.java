package com.code.demo.Dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author yan.kefei
 * @date 2018/6/25 16:38
 */
public class Provider {

    public static void main(String[] args) {
        try {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("provider.xml");
            System.out.println(context.getDisplayName() + ": here");
            context.start();
            System.out.println("服务已经启动...");
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
