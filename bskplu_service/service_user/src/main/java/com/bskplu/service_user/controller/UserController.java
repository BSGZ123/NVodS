package com.bskplu.service_user.controller;

import com.bskplu.api_user.RemoteUserClient;
import com.bskplu.api_user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: UserController
 * @Description: 门户系统 用户登录处理
 * @Author BsKPLu
 * @Date 2022/2/17
 * @Version 1.1
 */
@RestController
@RequestMapping("/service_user/user")
@RequiredArgsConstructor
public class UserController implements RemoteUserClient {
    @Override
    public User getLoginInfo(String id) {
        return null;
    }
}
