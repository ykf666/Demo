package com.code.basic.SpringAOP;

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

    /**
     * execution(* com.sample.service.impl..*.*(..))
     * 第一个"*"表示返回值类型任意
     * com.sample.service表示包名
     * ".."表示当前包及其子包
     * 第二个"*"表示任意类
     * 第三个"*"表示任意方法
     * *(..)表示任意参数的任意方法
    */
    @After("execution(* com.code.basic.SpringAOP.*.say())")
    public void log(){
        System.out.println("记录日志aop...");
    }
}
