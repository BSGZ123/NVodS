package com.bskplu.api_websocket;

import com.bskplu.api_websocket.factory.WebSocketFactory;
import com.bskplu.common_utils.constant.CloudConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @InterfaceName: RemoteWebsocketClient
 * @Description: api接口
 * @Author BsKPLu
 * @Date 2022/2/21
 * @Version 1.1
 */
@FeignClient(value = CloudConstant.WEBSOCKET,fallbackFactory = WebSocketFactory.class)
public interface RemoteWebsocketClient {
    @GetMapping("/service_websocket/webSocket/sendMessage/{logName}/{msg}")
    public void sendMessage(@PathVariable("logName") String logName, @PathVariable("msg") String msg);
}
