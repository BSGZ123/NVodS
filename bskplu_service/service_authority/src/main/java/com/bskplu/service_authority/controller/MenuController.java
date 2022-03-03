package com.bskplu.service_authority.controller;

import com.bskplu.common_utils.utils.ResponseResult;
import com.bskplu.service_authority.entity.Menu;
import com.bskplu.service_authority.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: MenuController
 * @Description: 菜单权限 前端控制类
 * @Author BsKPLu
 * @Date 2022/3/1
 * @Version 1.1
 */
@Api(tags = "菜单管理")
@RestController
@RequestMapping("/service_authority/admin/menu")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    @ApiModelProperty(value = "获取菜单列表")
    @PostMapping("/getMenuList")
    public ResponseResult getMenuList(){
        return ResponseResult.ok().data(menuService.getMenuList());
    }

    @ApiModelProperty(value = "根据ID删除所有子节点菜单")
    @PostMapping("/removeMenuById/{id}")
    public ResponseResult removeMenuById(@PathVariable String id){
        return menuService.removeMenuById(id);
    }

    @ApiModelProperty(value = "根据角色查询菜单")
    @GetMapping("/getMenuWithRoleId/{roleId}")
    public ResponseResult getMenuWithRoleId(@PathVariable String roleId){
        return ResponseResult.ok().data(menuService.selectMenuByRoleId(roleId));
    }

    @ApiModelProperty(value = "给用户角色赋予菜单权限")
    @PostMapping("/doAssignRoleAuth/{roleId}/{menuIds}")
    public ResponseResult doAssignRoleAuth(@PathVariable String roleId,@PathVariable String... menuIds){
        return menuService.saveRoleMenuRelationShip(roleId, menuIds);
    }

    @ApiModelProperty(value = "保存菜单")
    @PostMapping("/saveMenu")
    public ResponseResult save(@RequestBody Menu menu){
        return  ResponseResult.toOk(menuService.save(menu));
    }

    @ApiModelProperty(value = "更新菜单")
    @PostMapping("/updateMenu")
    public ResponseResult update(@RequestBody Menu menu){
        return ResponseResult.toOk(menuService.updateById(menu));
    }

}
