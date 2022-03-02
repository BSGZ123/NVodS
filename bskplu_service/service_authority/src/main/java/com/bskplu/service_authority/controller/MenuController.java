package com.bskplu.service_authority.controller;

import com.bskplu.common_utils.utils.ResponseResult;
import com.bskplu.service_authority.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
