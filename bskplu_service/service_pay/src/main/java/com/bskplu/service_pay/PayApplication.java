package com.bskplu.service_pay;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName: PayApplication
 * @Description: 支付模块启动类
 * @Author BsKPLu
 * @Date 2022/3/18
 * @Version 1.1
 */
@SpringBootApplication
@MapperScan("com.bskplu.**.mapper")
@ComponentScan(basePackages = {"com.bskplu"})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.bskplu"})
public class PayApplication {
    public static void main(String[] args) {
        SpringApplication.run(PayApplication.class,args);
    }
}
