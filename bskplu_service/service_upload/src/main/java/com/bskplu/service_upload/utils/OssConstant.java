package com.bskplu.service_upload.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName: OssConstant
 * @Description: 对象存储常用变量
 * @Author BsKPLu
 * @Date 2022/3/7
 * @Version 1.1
 */
@Component
public class OssConstant implements InitializingBean {

    @Value("${oss.endpoint}")
    private String endpoint;

    @Value("${oss.accessKeyId}")
    private String accessKeyId;

    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${oss.bucketName}")
    private String bucketName;

    @Value("${oss.path}")
    private String pathName;


    public static String ENDPOINT;
    public static String ASSESS_KEY_ID;
    public static String ASSESS_KEY_SECRET;
    public static String BUCKET_NAME;
    public static String PATH_NAME;


    /**
     * 在属性文件加载完毕且属性也设置完毕后，将会自动调用
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        ENDPOINT=endpoint;
        ASSESS_KEY_ID=accessKeyId;
        ASSESS_KEY_SECRET=accessKeySecret;
        BUCKET_NAME=bucketName;
        PATH_NAME=pathName;
    }
}
