package com.bskplu.service_authority.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bskplu.service_authority.entity.User;
import com.bskplu.service_authority.mapper.UserMapper;
import com.bskplu.service_authority.service.UserService;
import com.bskplu.service_base.utils.text.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @ClassName: UserServiceImpl
 * @Description: 用户服务实现类
 * @Author BsKPLu
 * @Date 2022/3/4
 * @Version 1.1
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public void selectPage(Page<User> pageParam, User userQueryVo) {
        final LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(userQueryVo.getUsername()),User::getUsername,userQueryVo.getUsername());
        baseMapper.selectPage(pageParam,wrapper);
    }

    @Override
    public User selectByUsername(String username) {
        return null;
    }
}
