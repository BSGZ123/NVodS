package com.bskplu.service_authority.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bskplu.common_utils.utils.ResponseResult;
import com.bskplu.service_authority.entity.Role;
import com.bskplu.service_authority.service.RoleService;
import com.bskplu.service_base.utils.text.StringUtils;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: RoleController
 * @Description: 角色控制类
 * @Author BsKPLu
 * @Date 2022/3/3
 * @Version 1.1
 */
@RestController
@RequestMapping("/service_authority/admin/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @ApiOperation(value = "获取角色列表的分页指定记录数")
    @GetMapping("/getRolePageList/{page}/{limit}")
    public ResponseResult index(@ApiParam(name="page",value = "当前页码",required = true)
                                @PathVariable Long page,
                                @ApiParam(name = "limit",value = "每页记录数",required = true)
                                @PathVariable Long limit,
                                Role role){
        Page<Role> rolePage =new Page<>(page,limit);
        QueryWrapper<Role> wrapper =new QueryWrapper<>();
        if(!StringUtils.isEmpty(role.getRoleName())){
            wrapper.like("role_name",role.getRoleName());
        }
        roleService.page(rolePage,wrapper);
        return ResponseResult.ok().data("items",rolePage.getRecords()).data("total",rolePage.getRecords());
    }
    @ApiOperation(value = "获取角色")
    @GetMapping("/getRoleById/{id}")
    public ResponseResult get(@PathVariable String id){
        Role role=roleService.getById(id);
        return ResponseResult.ok().data("item",role);
    }

    @ApiOperation(value = "保存角色信息")
    @PostMapping("/saveRole")
    public ResponseResult save(@RequestBody Role role){
        roleService.save(role);
        return ResponseResult.ok();
    }
    @ApiOperation(value = "修改角色信息")
    @PostMapping("/updateRole")
    public ResponseResult updateById(@RequestBody Role role){
        roleService.updateById(role);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "删除角色信息")
    @PostMapping("/removeRole/{id}")
    public ResponseResult remove(@PathVariable String id){
        roleService.removeById(id);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "根据id列表删除角色信息")
    @PostMapping("/batchRemove")
    public ResponseResult batchRemove(@RequestBody List<String> idList){
        roleService.removeByIds(idList);
        return ResponseResult.ok();
    }


}
