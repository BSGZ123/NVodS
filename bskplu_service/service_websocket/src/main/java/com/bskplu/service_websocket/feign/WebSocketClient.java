package com.bskplu.service_websocket.feign;

import com.bskplu.api_websocket.RemoteWebsocketClient;
import com.bskplu.service_websocket.server.WebSocketServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: WebsocketClient
 * @Description: 客户端类
 * @Author BsKPLu
 * @Date 2022/3/5
 * @Version 1.1
 */
@RestController
@RequestMapping("/service_websocket/webSocket")
public class WebSocketClient implements RemoteWebsocketClient {

    /**
     * 调用推送服务到前端
     * @param logName
     * @param msg
     */
    @Override
    @GetMapping("sendMessage/{logName}/{msg}")
    public void sendMessage(@PathVariable("logName") String logName, @PathVariable("msg") String msg) {
        WebSocketServer.sendInfo(logName,msg);
    }
}
