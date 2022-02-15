package com.bskplu.service_security.annotation;

import org.springframework.cloud.openfeign.EnableFeignClients;

import java.lang.annotation.*;

//作用范围 类接口枚举注解等等
@Target(ElementType.TYPE)
//在jvm运行时可以使用 用于运行时反射等
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableFeignClients
public @interface EnableBsFeignClients {
    String[] value() default {};

    String[] basePackages() default { "com.bskplu" };

    Class<?>[] basePackageClasses() default {};

    Class<?>[] defaultConfiguration() default {};

    Class<?>[] clients() default {};
}
