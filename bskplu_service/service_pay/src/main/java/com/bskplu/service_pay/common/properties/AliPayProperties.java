package com.bskplu.service_pay.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName: AliPayProperties
 * @Description: 阿里支付属性类
 * @Author BsKPLu
 * @Date 2022/3/19
 * @Version 1.1
 */
@Data
@Component
@ConfigurationProperties(prefix = "pay.alibaba")
public class AliPayProperties {
    /**
     * 应用程序id
     */
    private String appId;
    /**
     * 私钥
     */
    private String privateKey;
    /**
     * 公钥
     */
    private String publicKey;
    /**
     * 支付宝服务地址
     */
    private String serverUrl;
    /**
     * 回调地址
     */
    private String returnUrl;
    /**
     * 异步回调
     */
    private String notifyUrl;
}
