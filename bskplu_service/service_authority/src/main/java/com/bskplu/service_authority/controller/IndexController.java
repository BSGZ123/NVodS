package com.bskplu.service_authority.controller;

import com.alibaba.fastjson.JSONObject;
import com.bskplu.common_utils.utils.ResponseResult;
import com.bskplu.service_authority.service.IndexService;
import com.bskplu.service_security.utils.SpringSecurityBean;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: IndexController
 * @Description: 用户登录入口
 * @Author BsKPLu
 * @Date 2022/2/28
 * @Version 1.1
 */
@RestController
@Slf4j
@RequestMapping("/service_authority/admin/index")
@RequiredArgsConstructor
public class IndexController {
    private final IndexService indexService;

    /**
     * 根据token用户名获取用户信息
     * @return
     */
    @GetMapping("info")
    public ResponseResult info(){
        String username= SpringSecurityBean.userName();
        Map<String, Object> userInfo = indexService.getUserInfo(username);
        return ResponseResult.ok().data(userInfo);
    }

    /**
     * 根据用户角色获取菜单列表
     * @return
     */
    @GetMapping("menu")
    public ResponseResult getMenu(){
        String username=SpringSecurityBean.userName();
        List<JSONObject> menuList=indexService.getMenuByUserName(username);
        return ResponseResult.ok().data("menuList",menuList);
    }
}
