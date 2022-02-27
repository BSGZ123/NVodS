package com.bskplu.service_authority.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.bskplu.common_utils.utils.ResultCode;
import com.bskplu.service_authority.entity.Role;
import com.bskplu.service_authority.entity.User;
import com.bskplu.service_authority.service.IndexService;
import com.bskplu.service_authority.service.MenuService;
import com.bskplu.service_authority.service.RoleService;
import com.bskplu.service_authority.service.UserService;
import com.bskplu.service_base.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: IndexServiceImpl
 * @Description: indexService接口实现类
 * @Author BsKPLu
 * @Date 2022/2/27
 * @Version 1.1
 */
@Component
@RequiredArgsConstructor
public class IndexServiceImpl implements IndexService {
    private final UserService userService;
    private final RoleService roleService;
    private final MenuService menuService;
    private final RedisTemplate redisTemplate;

    //http://129.211.209.210/demo1.html
    /**
     * 获取用户信息
     * @param username
     * @return
     */
    @Override
    public Map<String, Object> getUserInfo(String username) {
        final HashMap<String, Object> result =new HashMap<>(14);
        User user=userService.selectByUsername(username);
        if(ObjectUtils.isEmpty(user)){
            throw new BusinessException(ResultCode.ERROR,"用户名或者密码错误");
        }

        // 根据用户ID获取拥有的角色
        final List<Role> roles = roleService.selectRoleByUserId(user.getId());
        // 根据用户ID获取拥有的权限
        final List<String> permissionValueList = menuService.selectPermissionValueByUserId(user.getId());
        // 把权限缓存到redis当中
        redisTemplate.opsForValue().set(username, permissionValueList);
        // 组装返回前端参数
        result.put("name", user.getUsername());
        result.put("avatar", "http://129.211.209.210/demo1.html");
        result.put("roles", roles.stream().map(Role::getRoleName).collect(Collectors.toList()));
        result.put("permissionValueList", permissionValueList);
        return result;
    }

    /**
     * 根据用户名称查询用户菜单
     * @param username
     * @return
     */
    @Override
    public List<JSONObject> getMenuByUserName(String username) {
        User user=userService.selectByUsername(username);

        //根据用户id拉取用户所拥有的菜单权限
        List<JSONObject> permissionList=menuService.selectPermissionByUserId(user.getId());
        return permissionList;
    }
}
