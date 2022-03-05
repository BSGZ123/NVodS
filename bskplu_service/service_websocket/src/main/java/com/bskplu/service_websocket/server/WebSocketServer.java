package com.bskplu.service_websocket.server;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @ClassName: WebSocketServer
 * @Description: 前端请求服务地址
 * @Author BsKPLu
 * @Date 2022/3/5
 * @Version 1.1
 */
@ServerEndpoint("/service_websocket/wspoint/{loginName}")
@Component
public class WebSocketServer {
    //存储每一个链接
    private static final CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<>();

    private Session session;
    private String loginName="";

    @OnOpen
    public void onOpen(Session session,@PathParam("loginName") String loginName){
        //前端连接获取的登录名和会话
        this.loginName=loginName;
        this.session=session;

        webSocketSet.add(this);
        try{
            sendMessage("success");
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    /**
     * 关闭连接
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("接收到来自[" + message + "]发送的消息" + session);
        // 发送消息
//        for (WebSocketServer item : webSocketSet) {
//            try {
//                item.sendMessage(message + ",时间:" + new Date() + session);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    /**
     * 发送消息
     * @param message
     */
    public void sendMessage(String message) {
        try {
            if (this.session != null) {
                synchronized (this.session) {
                    this.session.getBasicRemote().sendText(message);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送消息给指定的用户
     * @param userName
     * @param msgStr
     */
    public static void sendInfo(String userName, String msgStr) {
        for (WebSocketServer item : webSocketSet) {
            if (item.loginName.equals(userName)) {
                item.sendMessage(msgStr);
            }
        }
    }
}
