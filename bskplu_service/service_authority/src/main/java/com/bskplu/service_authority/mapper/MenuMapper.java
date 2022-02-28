package com.bskplu.service_authority.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bskplu.service_authority.entity.Menu;

import java.util.List;

/**
 * @InterfaceName: MenuMapper
 * @Description: 菜单权限 Mapper接口
 * @Author BsKPLu
 * @Date 2022/2/28
 * @Version 1.1
 */
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 根据用户Id查询用户使用的菜单
     * @param id
     * @return
     */
    List<Menu> selectMenuByUserId (String id);

    /**
     * 管理员拥有所有菜单
     * @return
     */
    List<String> selectAllMenuValue ();

    /**
     * 根据用户id查询用户拥有的菜单权限信息
     * @param id
     * @return
     */
    List<String> selectMenuValueByUserId (String id);
}
