package com.bskplu.api_websocket.factory;

import com.bskplu.api_websocket.RemoteWebsocketClient;
import feign.hystrix.FallbackFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * @ClassName: WebSocketFactory
 * @Description: WebSocketFactory
 * @Author BsKPLu
 * @Date 2022/2/21
 * @Version 1.1
 */
@Component
public class WebSocketFactory implements FallbackFactory<RemoteWebsocketClient> {
    private static final Logger logger= LogManager.getLogger(WebSocketFactory.class);
    @Override
    public RemoteWebsocketClient create(Throwable cause) {
        logger.error("websocket服务模块错误:{}", cause.getLocalizedMessage());
        return new RemoteWebsocketClient() {
            @Override
            public void sendMessage(String logName, String msg) {

            }
        };
    }
}
