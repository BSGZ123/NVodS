package com.bskplu.service_security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @ClassName: TokenWebSecurityConfig
 * @Description: springSecurity配置
 * @Author BsKPLu
 * @Date 2022/2/15
 * @Version 1.1
 */

@Configuration
@EnableWebSecurity //启用web安全配置
@EnableGlobalMethodSecurity(prePostEnabled = true)//方法执行前进行验证
public class TokenWebSecurityConfig extends WebSecurityConfigurerAdapter {

}
