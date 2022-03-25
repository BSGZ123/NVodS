package com.bskplu.service_authority.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bskplu.common_utils.constant.Constants;
import com.bskplu.common_utils.utils.ResponseResult;
import com.bskplu.service_authority.entity.Menu;
import com.bskplu.service_authority.entity.RoleMenu;
import com.bskplu.service_authority.entity.User;
import com.bskplu.service_authority.mapper.MenuMapper;
import com.bskplu.service_authority.service.MenuService;
import com.bskplu.service_authority.service.RoleMenuService;
import com.bskplu.service_authority.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * 菜单列表
     * @return
     */
    @Override
    public List<Menu> getMenuList() {
        return buildTreeMenuList(baseMapper.selectList(new LambdaQueryWrapper<Menu>()));
    }

    /**
     *通过id删除菜单 1.根据id查出子结点 2.批量删除
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult removeMenuById(String id) {
        return ResponseResult.toOk(baseMapper.deleteBatchIds(deleteChildrenMenu(id))>0);
    }

    /**
     * 根据角色Id查询菜单
     * @param roleId
     * @return
     */
    @Override
    public List<Menu> selectMenuByRoleId(String roleId) {
        // 1. 查询出全部的菜单
        final List<Menu> menus = baseMapper.selectList(new LambdaQueryWrapper<Menu>());
        final List<RoleMenu> roleMenus = roleMenuService.list(new LambdaQueryWrapper<RoleMenu>().eq(RoleMenu::getRoleId, roleId));
        // 2. 根据角色关联表ID查询菜单Id
        for (Menu menu : menus) {
            for (RoleMenu roleMenu : roleMenus) {
                if (menu.getId().equals(roleMenu.getPermissionId())) {
                    //3.设为选中状态，方便下一步处理
                    menu.setSelect(true);
                }
            }
        }
        return buildTreeMenuList(menus);
    }

    /**
     * 保存角色菜单关系
     * @param roleId
     * @param menuIds
     * @return
     */
    @Override
    public ResponseResult saveRoleMenuRelationShip(String roleId, String[] menuIds) {
        roleMenuService.remove(new LambdaQueryWrapper<RoleMenu>().eq(RoleMenu::getRoleId,roleId));
        final List<RoleMenu> list=new ArrayList<>();
        Arrays.stream(menuIds).forEach(e -> {
            list.add(new RoleMenu().setRoleId(roleId).setPermissionId(e));
        });
        return ResponseResult.toOk(roleMenuService.saveBatch(list));
    }

    /**
     * 根据用户id查询用户所具备的权限内容
     * @param id
     * @return
     */
    @Override
    public List<String> selectPermissionValueByUserId(String id) {
        List<String> selectPermissionValueList=null;
        if (this.isSysAdmin(id)) {
            //如果是系统管理员，则获取所有菜单权限
            selectPermissionValueList = baseMapper.selectAllMenuValue();
        } else {
            //不是管理员，就根据用户查询菜单权限
            selectPermissionValueList = baseMapper.selectMenuValueByUserId(id);
        }
        return selectPermissionValueList;
    }

    /**
     * 根据用户id查询用户所具有的菜单 返回给前端
     * @param id
     * @return
     */
    @Override
    public List<JSONObject> selectPermissionByUserId(String id) {
        List<Menu> selectMenuList;
        if(this.isSysAdmin(id)){//同上一个方法
            selectMenuList=baseMapper.selectList(null);
        }else{
            selectMenuList=baseMapper.selectMenuByUserId(id);
        }
        //1.构建树形菜单
        List<Menu> menuList = buildTreeMenuList(selectMenuList);
        //2.返回给前端json数据
        return buildMenuWebList(menuList);

    }

    /**
     * 构造路由菜单
     * @param menuList
     * @return
     */
    public List<JSONObject> buildMenuWebList(List<Menu> menuList){
        List<JSONObject> menus =new ArrayList<>();
        if (menuList.size() == 1) {
            Menu topNode = menuList.get(0);
            //左侧一级菜单
            List<Menu> oneMenuList = topNode.getChildMenuList();
            for (Menu one : oneMenuList) {
                JSONObject oneMenu = new JSONObject();
                oneMenu.put("path", one.getPath());
                oneMenu.put("component", one.getComponent());
                oneMenu.put("redirect", "noredirect");
                oneMenu.put("name", "name_" + one.getId());
                oneMenu.put("hidden", false);

                JSONObject oneMeta = new JSONObject();
                oneMeta.put("title", one.getName());
                oneMeta.put("icon", one.getIcon());
                oneMenu.put("meta", oneMeta);

                List<JSONObject> children = new ArrayList<>();
                List<Menu> twoMenuList = one.getChildMenuList();
                for (Menu two : twoMenuList) {
                    JSONObject twoMenu = new JSONObject();
                    twoMenu.put("path", two.getPath());
                    twoMenu.put("component", two.getComponent());
                    twoMenu.put("name", "name_" + two.getId());
                    twoMenu.put("hidden", false);

                    JSONObject twoMeta = new JSONObject();
                    twoMeta.put("title", two.getName());
                    twoMenu.put("meta", twoMeta);

                    children.add(twoMenu);

                    List<Menu> threeMenuList = two.getChildMenuList();
                    for (Menu three : threeMenuList) {
                        if (StringUtils.isEmpty(three.getPath())) continue;

                        JSONObject threeMenu = new JSONObject();
                        threeMenu.put("path", three.getPath());
                        threeMenu.put("component", three.getComponent());
                        threeMenu.put("name", "name_" + three.getId());
                        threeMenu.put("hidden", true);

                        JSONObject threeMeta = new JSONObject();
                        threeMeta.put("title", three.getName());
                        threeMenu.put("meta", threeMeta);

                        children.add(threeMenu);
                    }
                }
                oneMenu.put("children", children);
                menus.add(oneMenu);
            }
        }
        return menus;
    }

    /**
     * 构建菜单树列表
     * @param menus
     * @return
     */
    private List<Menu> buildTreeMenuList(List<Menu> menus){
        return menus.stream().filter(e -> e.getPid().equals(Constants.MENU_PID)).map(e -> {
            e.setLevel(1);
            e.setChildMenuList(buildMenuList(e, menus));
            return e;
        }).collect(Collectors.toList());
    }

    /**
     * 递归查找获取到的子菜单的子菜单
     * @param menu 当前的菜单项
     * @param menus all菜单集合
     * @return
     */
    private List<Menu> buildMenuList(Menu menu, List<Menu> menus) {
        return menus.stream().filter(e -> e.getPid().equals(menu.getId()))//过滤查询List，获得符合的对象集合
                .map(e -> {
                    // 用于前端渲染树形菜单
                    e.setLevel(menu.getLevel() + 1);
                    e.setChildMenuList(buildMenuList(e, menus));
                    return e;
                }).collect(Collectors.toList());
    }

    /**
     * 删除子结点菜单
     * @param id
     * @return
     */
    private List<String> deleteChildrenMenu(String id){
        return baseMapper.selectList(new LambdaQueryWrapper<Menu>().eq(Menu::getPid, id).select(Menu::getId)).stream()
                .map(e -> {
                    deleteChildrenMenu(e.getId());
                    return e;
                }).map(Menu::getId).collect(Collectors.toList());
    }

    private Boolean isSysAdmin(String userId){
        User user=userService.getById(userId);

        if(user != null && Constants.ADMIN.equalsIgnoreCase(user.getUsername())){
            return true;
        }
        return false;
    }
}
