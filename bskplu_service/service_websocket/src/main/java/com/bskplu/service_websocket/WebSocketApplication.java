package com.bskplu.service_websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName: WebSocketApplication
 * @Description: service_websocket服务启动类
 * @Author BsKPLu
 * @Date 2022/3/5
 * @Version 1.1
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
public class WebSocketApplication {
    public static void main(String[] args){
        SpringApplication.run(WebSocketApplication.class,args);
    }
}
