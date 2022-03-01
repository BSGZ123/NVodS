package com.bskplu.service_authority.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bskplu.common_utils.utils.ResponseResult;
import com.bskplu.service_authority.entity.Menu;
import com.bskplu.service_authority.mapper.MenuMapper;
import com.bskplu.service_authority.service.MenuService;
import com.bskplu.service_authority.service.RoleMenuService;
import com.bskplu.service_authority.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: MenuServiceImpl
 * @Description: 菜单权限 实现类
 * @Author BsKPLu
 * @Date 2022/2/28
 * @Version 1.1
 */
@Service
@RequiredArgsConstructor
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    private final RoleMenuService roleMenuService;
    private final UserService userService;
    @Override
    public List<Menu> getMenuList() {
        return null;
    }

    @Override
    public ResponseResult removeMenuById(String id) {
        return null;
    }

    @Override
    public List<Menu> selectMenuByRoleId(String roleId) {
        return null;
    }

    @Override
    public ResponseResult saveRoleMenuRelationShip(String roleId, String[] menuIds) {
        return null;
    }

    @Override
    public List<String> selectPermissionValueByUserId(String id) {
        return null;
    }

    @Override
    public List<JSONObject> selectPermissionByUserId(String id) {
        return null;
    }


}
