package com.bskplu.service_upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName: UploadApplication
 * @Description: 上传功能模块启动类
 * @Author BsKPLu
 * @Date 2022/3/7
 * @Version 1.1
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
public class UploadApplication {
    public static void main(String args[]){
        SpringApplication.run(UploadApplication.class,args);
    }

}
