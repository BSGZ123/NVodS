package com.bskplu.service_base.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;

/**
 * @ClassName: MybatisPlusAppConfig
 * @Description: MybatisPlus基础配置
 * @Author BsKPLu
 * @Date 2022/2/14
 * @Version 1.1
 */
public class MybatisPlusAppConfig {
    /**
     * mybatisPlus逻辑删除
     * 逻辑 and 物理 ？
     * @return
     */
    @Bean
    public ISqlInjector sqlInjector () {
        return new LogicSqlInjector();
    }


    /**
     * 配置分页拦截器
     * 暂时还不知道如何用
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor () {
        return new PaginationInterceptor();
    }
}
