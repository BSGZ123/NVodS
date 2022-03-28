package com.bskplu.service_authority;

import com.bskplu.service_security.annotation.EnableAuthorizeConfig;
import com.bskplu.service_security.annotation.EnableBsFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @ClassName: AuthorityApplication
 * @Description: 鉴权启动类
 * @Author BsKPLu
 * @Date 2022/2/27
 * @Version 1.1
 */
@EnableAuthorizeConfig
@EnableBsFeignClients
@SpringCloudApplication
public class AuthorityApplication {
    public static void main(String args[]){
        SpringApplication.run(AuthorityApplication.class, args);
    }
}
