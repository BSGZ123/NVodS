package com.bskplu.service_user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bskplu.api_user.entity.User;
import com.bskplu.service_base.controller.core.vo.LoginVo;
import com.bskplu.service_base.controller.core.vo.RegisterVo;

/**
 * @InterfaceName: UserService
 * @Description: 用户业务服务接口
 * @Author BsKPLu
 * @Date 2022/2/17
 * @Version 1.1
 */
public interface UserService extends IService<User> {

    /**
     * 登录签token
     * @param loginVo
     * @return 返回token
     */
    String login(LoginVo loginVo);

    /**
     * 用户注册
     * @param registerVo
     * @return
     */
    String register(RegisterVo registerVo);

    /**
     * 根据openid查询用户
     * @param openId
     * @return
     */
    String getByOpenid(String openId);
}
