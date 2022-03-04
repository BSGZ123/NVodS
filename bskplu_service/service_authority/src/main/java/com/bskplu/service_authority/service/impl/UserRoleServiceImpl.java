package com.bskplu.service_authority.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bskplu.service_authority.entity.UserRole;
import com.bskplu.service_authority.mapper.UserRoleMapper;
import com.bskplu.service_authority.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * @ClassName: UserRoleServiceImpl
 * @Description: 用户角色服务实现类
 * @Author BsKPLu
 * @Date 2022/3/4
 * @Version 1.1
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
}
