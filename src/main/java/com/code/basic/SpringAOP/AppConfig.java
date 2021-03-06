package com.code.basic.SpringAOP;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author yan.kefei
 * @date 2018/5/27 23:26
 */
@Configuration
@ComponentScan(basePackages = {"com.code.basic.SpringAOP"})
@EnableAspectJAutoProxy(proxyTargetClass = false)
public class AppConfig {

}
