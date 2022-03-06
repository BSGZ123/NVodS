package com.bskplu.service_vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName: VodApplication
 * @Description: 视频点播服务启动类
 * @Author BsKPLu
 * @Date 2022/3/6
 * @Version 1.1
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
public class VodApplication {
    public static void main(String args[]){
        SpringApplication.run(VodApplication.class,args);
    }
}
