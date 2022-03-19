package com.bskplu.service_pay.common.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.bskplu.service_pay.common.properties.AliPayProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: AliPayApiConfig
 * @Description: 阿里支付配置类
 * @Author BsKPLu
 * @Date 2022/3/19
 * @Version 1.1
 */
@Configuration
public class AliPayApiConfig {

    @Autowired
    private AliPayProperties aliPayProperties;

    private static final String JSON = "json";
    private static final String RSA2 = "RSA2";
    private static final String CHARSET = "UTF-8";

    /**
     * 获取实例对象
     * @return
     */
    public AlipayClient getAliPayClient(){
        return new DefaultAlipayClient(
                aliPayProperties.getServerUrl(),
                aliPayProperties.getAppId(),
                aliPayProperties.getPrivateKey(),
                JSON,
                CHARSET,
                aliPayProperties.getPublicKey(),
                RSA2
        );
    }
}
