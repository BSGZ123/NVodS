package com.bskplu.service_authority.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bskplu.common_utils.constant.Constants;
import com.bskplu.common_utils.utils.ResponseResult;
import com.bskplu.service_authority.entity.Menu;
import com.bskplu.service_authority.mapper.MenuMapper;
import com.bskplu.service_authority.service.MenuService;
import com.bskplu.service_authority.service.RoleMenuService;
import com.bskplu.service_authority.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
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
        return menus.stream().filter(e -> e.getPid().equals(menu.getId()))
                .map(e -> {
                    // 用于前端渲染
                    e.setLevel(menu.getLevel() + 1);
                    e.setChildMenuList(buildMenuList(e, menus));
                    return e;
                }).collect(Collectors.toList());
    }


}
