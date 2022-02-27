package com.bskplu.service_authority.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bskplu.service_authority.entity.User;

/**
 * @InterfaceName: UserService
 * @Description: 用户服务接口类
 * @Author BsKPLu
 * @Date 2022/2/27
 * @Version 1.1
 */
public interface UserService extends IService<User> {
    /**
     * 分页查询用户
     * @param pageParam
     * @param userQueryVo
     */
    void selectPage (Page<User> pageParam, User userQueryVo);

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    User selectByUsername (String username);
}
