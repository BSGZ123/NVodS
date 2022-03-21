package com.bskplu.api_user.factory;

import com.bskplu.api_user.RemoteUserClient;
import com.bskplu.api_user.entity.User;
import feign.hystrix.FallbackFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * @ClassName: RemoteUserFactory
 * @Description: user_api错误熔断工厂类
 * @Author BsKPLu
 * @Date 2022/2/16
 * @Version 1.1
 */
@Component
public class RemoteUserFactory implements FallbackFactory<RemoteUserClient> {

    private static final Logger logger = LogManager.getLogger(RemoteUserFactory.class);

    @Override
    public RemoteUserClient create(Throwable cause) {
        logger.error("门户用户模块错误:{}",cause.getLocalizedMessage());
        return new RemoteUserClient() {
            @Override
            public User getLoginInfo(String id) {
                return null;
            }
        };
    }
}
