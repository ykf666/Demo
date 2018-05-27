package com.code.demo.springAOP;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author yan.kefei
 * @date 2018/5/27 23:39
 */
@Component
@Aspect
public class LogAOP {

    @After("execution(* com.code.demo.springAOP.*.say())")
    public void log(){
        System.out.println("记录日志aop...");
    }
}
