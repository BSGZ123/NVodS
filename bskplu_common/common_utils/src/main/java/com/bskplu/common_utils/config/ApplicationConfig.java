package com.bskplu.common_utils.config;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;

import java.util.TimeZone;

/**
 * @ClassName: ApplicationConfig
 * @Description: 系统配置
 * @Author BsKPLu
 * @Date 2022/2/13
 * @Version 1.1
 */
public class ApplicationConfig {
//    设置时区
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer(){
        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder.timeZone(TimeZone.getDefault());
    }
}
