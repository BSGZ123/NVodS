package com.bskplu.service_user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName: UserApplication
 * @Description: 用户服务启动类
 * @Author BsKPLu
 * @Date 2022/2/16
 * @Version 1.1
 */
@SpringBootApplication
// 扫描目录下mapper
@MapperScan("com.bskplu.**.mapper")
//扫描加载包
@ComponentScan(basePackages = {"com.bskplu"})
//启用服务发现注册
@EnableDiscoveryClient
//开启远程调用
@EnableFeignClients(basePackages = {"com.bskplu"})
public class UserApplication {
    public static void main(String args[]){
        SpringApplication.run(UserApplication.class,args);
    }
}
