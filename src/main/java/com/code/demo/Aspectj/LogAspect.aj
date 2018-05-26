package com.code.demo.Aspectj;

/**
 * @author yan.kefei
 * @date 2018/5/26 23:21
 */
public aspect LogAspect {

    pointcut logPointcut():execution(void SayService.say());
    after():logPointcut(){
        System.out.println("记录日志 ...");
    }

}
