package com.bskplu.service_vod.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName: VodConstant
 * @Description: 点播常量
 * @Author BsKPLu
 * @Date 2022/3/6
 * @Version 1.1
 */
@Component
public class VodConstant implements InitializingBean {
    @Value("${aliyun.keyid}")
    private String keyId;

    @Value("${aliyun.keysecret}")
    private String keySecret;

    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;

    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY_ID = keyId;
        ACCESS_KEY_SECRET = keySecret;
    }
}
