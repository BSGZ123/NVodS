package com.bskplu.service_authority.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bskplu.common_utils.utils.ResponseResult;
import com.bskplu.service_authority.entity.Menu;

import java.util.List;

/**
 * @InterfaceName: MenuService
 * @Description: 菜单权限接口类
 * @Author BsKPLu
 * @Date 2022/2/27
 * @Version 1.1
 */
public interface MenuService extends IService<Menu> {

    /**
     * 获取菜单列表
     * @return
     */
    List<Menu> getMenuList ();

    /**
     * 通过id删除菜单
     * @param id
     * @return
     */
    ResponseResult removeMenuById (String id);

    /**
     * 根据角色id查询菜单
     * @param roleId
     * @return
     */
    List<Menu> selectMenuByRoleId (String roleId);

    /**
     * 保存角色菜单关系
     * @param roleId
     * @param menuIds
     * @return
     */
    ResponseResult saveRoleMenuRelationShip (String roleId, String[] menuIds);

    /**
     * 根据用户id确认用户拥有权限
     * @param id
     * @return
     */
    List<String> selectPermissionValueByUserId (String id);

    /**
     * 根据用户id查询菜单权限
     * @param id
     * @return
     */
    List<JSONObject> selectPermissionByUserId (String id);
}
