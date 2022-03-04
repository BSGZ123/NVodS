package com.bskplu.service_authority.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bskplu.service_authority.entity.Role;
import com.bskplu.service_authority.entity.UserRole;
import com.bskplu.service_authority.mapper.RoleMapper;
import com.bskplu.service_authority.service.RoleService;
import com.bskplu.service_authority.service.UserRoleService;
import com.bskplu.service_base.utils.text.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: RoleServiceImpl
 * @Description: 角色服务实现类
 * @Author BsKPLu
 * @Date 2022/3/3
 * @Version 1.1
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private UserRoleService userRoleService;

    /**
     * 根据用户id获取用户数据
     * @param userId
     * @return
     */
    @Override
    public Map<String, Object> findRoleByUserId (String userId) {
        //查询所有的角色
        List<Role> allRolesList = baseMapper.selectList(null);
        //根据用户id，查询用户拥有的角色id
        List<UserRole> existUserRoleList = userRoleService.list(
                new QueryWrapper<UserRole>().eq("user_id", userId).select("role_id")
        );
        List<String> existRoleList = existUserRoleList.stream().map(
                UserRole::getRoleId).collect(Collectors.toList()
        );
        // 对角色进行分类
        List<Role> assignRoles = new ArrayList<>();
        for (Role role : allRolesList) {
            //已分配
            if (existRoleList.contains(role.getId())) {
                assignRoles.add(role);
            }
        }
        Map<String, Object> roleMap = new HashMap<>();
        roleMap.put("assignRoles", assignRoles);
        roleMap.put("allRolesList", allRolesList);
        return roleMap;
    }

    /**
     * 根据用户id和角色id保存角色信息
     * @param userId
     * @param roleIds
     */
    @Override
    public void saveUserRoleRelationShip (String userId, String[] roleIds) {
        //删除原来关系
        userRoleService.remove(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId, userId));
        //重新建立关系
        List<UserRole> userRoleList = new ArrayList<>();
        for (String roleId : roleIds) {
            if (StringUtils.isEmpty(roleId)) { continue; }
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            userRoleList.add(userRole);
        }
        userRoleService.saveBatch(userRoleList);
    }

    @Override
    public List<Role> selectRoleByUserId (String id) {
        //根据用户id查询拥有的角色id
        List<UserRole> userRoleList = userRoleService
                .list(new LambdaQueryWrapper<UserRole>()
                        .eq(UserRole::getUserId, id)
                        .select(UserRole::getRoleId));
        //获取所有角色id
        List<String> roleIdList = userRoleList.stream()
                .map(item -> item.getRoleId())
                .collect(Collectors.toList());
        //根据角色id查询所有的角色信息
        List<Role> roleList = new ArrayList<>();
        if (roleIdList.size() > 0) {
            roleList = baseMapper.selectBatchIds(roleIdList);
        }
        return roleList;
    }

}