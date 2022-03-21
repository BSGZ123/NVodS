package com.bskplu.api_pay.factory;

import com.bskplu.api_pay.RemotePayOrderClient;
import feign.hystrix.FallbackFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * @ClassName: RemotePayOrderFactory
 * @Description: 支付异常处理工厂类
 * @Author BsKPLu
 * @Date 2022/3/1
 * @Version 1.1
 */
@Component
public class RemotePayOrderFactory implements FallbackFactory<RemotePayOrderClient> {

    private static final Logger logger = LogManager.getLogger(RemotePayOrderFactory.class);
    @Override
    public RemotePayOrderClient create(Throwable cause) {
        logger.error("支付模块请求失败:{}", cause.getLocalizedMessage());
        return new RemotePayOrderClient() {
            @Override
            public boolean getBuyContent (String userId, String contentId) {
                return false;
            }
        };
    }
}
