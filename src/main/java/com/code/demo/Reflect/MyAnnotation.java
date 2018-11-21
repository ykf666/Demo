package com.code.demo.Reflect;

import java.lang.annotation.*;

/**
 * @author yan.kefei
 * @date 2018/6/22 14:22
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Inherited
@Documented
public @interface MyAnnotation {

    String value() default "";

}
