package com.bskplu.service_authority.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bskplu.service_authority.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName: RoleService
 * @Description: 角色接口服务类
 * @Author BsKPLu
 * @Date 2022/2/27
 * @Version 1.1
 */
public interface RoleService extends IService<Role> {
    /**
     * 根据用户id查询用户角色 test
     * @param userId
     * @return
     */
    Map<String, Object> findRoleByUserId(String userId);

    /**
     * 依据用户id保存更新的角色
     * @param userId
     * @param roleId
     */
    void saveUserRoleRelationShip(String userId, String[] roleId);

    /**
     * 根据用户id查询用户角色
     * @param id
     * @return
     */
    List<Role> selectRoleByUserId (String id);
}
