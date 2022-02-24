package com.bskplu.service_cms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName: CmsApplication
 * @Description: 首页焦点启动类
 * @Author BsKPLu
 * @Date 2022/2/22
 * @Version 1.1
 */

@SpringBootApplication
@MapperScan("com.bskplu.**.mapper")
@ComponentScan(basePackages = {"com.bskplu"})
//开启服务发现注册
@EnableDiscoveryClient
public class CmsApplication {
    public static void main(String args[]){SpringApplication.run(CmsApplication.class,args);}
}
