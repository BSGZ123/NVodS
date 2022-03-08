package com.bskplu.service_video;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName: VideoApplication
 * @Description: 视频服务启动类
 * @Author BsKPLu
 * @Date 2022/3/8
 * @Version 1.1
 */
@SpringBootApplication
@MapperScan("com.bskplu.**.mapper")
@ComponentScan(basePackages = {"com.bskplu"})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.bskplu"})
public class VideoApplication {
    public static void main(String[] args) {
        SpringApplication.run(VideoApplication.class,args);
    }
}
