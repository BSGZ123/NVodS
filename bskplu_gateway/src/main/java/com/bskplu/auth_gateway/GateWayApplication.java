package com.bskplu.auth_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName: GateWayApplication
 * @Description: 网关入口
 * @Author BsKPLu
 * @Date 2022/2/25
 * @Version 1.1
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
public class GateWayApplication {
    public static void main(String [] args){
        SpringApplication.run(GateWayApplication.class,args);
    }
}
