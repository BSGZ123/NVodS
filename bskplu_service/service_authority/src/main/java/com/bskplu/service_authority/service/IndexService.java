package com.bskplu.service_authority.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName: IndexService
 * @Description: 首页 必备用户信息获取接口
 * @Author BsKPLu
 * @Date 2022/2/27
 * @Version 1.1
 */
public interface IndexService {
    /**
     * 获取用户信息
     * @param username
     * @return
     */
    Map<String, Object> getUserInfo (String username);

    /**
     * 获取用户的菜单信息
     * @param username
     * @return
     */
    List<JSONObject> getMenuByUserName (String username);
}
