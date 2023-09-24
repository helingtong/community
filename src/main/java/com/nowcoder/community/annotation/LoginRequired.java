package com.nowcoder.community.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于标识需要登录才能访问的方法的自定义注解。
 */
@Target(ElementType.METHOD) // 表示该注解可以应用于方法
@Retention(RetentionPolicy.RUNTIME) // 表示该注解在运行时保留
public @interface LoginRequired {

}