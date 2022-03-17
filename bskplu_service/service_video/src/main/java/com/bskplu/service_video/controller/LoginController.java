package com.bskplu.service_video.controller;

import com.bskplu.common_utils.utils.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: LoginController
 * @Description: 登录控制类
 * @Author BsKPLu
 * @Date 2022/3/16
 * @Version 1.1
 */
@RestController
@RequestMapping("/service_video/user")
public class LoginController {

    @PostMapping("/login")
    public ResponseResult login(){
        return ResponseResult.ok().data("token","admin-token");
    }

    @GetMapping("/info")
    public ResponseResult info () {
        return ResponseResult.ok()
                .data("roles", "[admin]")
                .data("name", "fmjava")
                .data("avatar", "http://129.211.209.210/torture.gif");
    }


}
