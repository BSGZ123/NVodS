package com.bskplu.service_authority.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bskplu.api_websocket.RemoteWebsocketClient;
import com.bskplu.common_utils.utils.ResponseResult;
import com.bskplu.service_authority.entity.User;
import com.bskplu.service_authority.service.RoleService;
import com.bskplu.service_authority.service.UserService;
import com.bskplu.service_security.utils.SpringSecurityBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * @ClassName: UserController
 * @Description: 用户前端控制器
 * @Author BsKPLu
 * @Date 2022/3/4
 * @Version 1.1
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/service_authority/admin/user")
@RequiredArgsConstructor
@RefreshScope
public class UserController {
    private final UserService userService;
    private final RoleService roleService;
    private final RemoteWebsocketClient remoteWebsocketClient;

    @Value("${useLocalCache:false}")
    private Boolean useLocalCache;

    @RequestMapping("/getConfig/{id}")
    public boolean get(@PathVariable("id") String id) {
        System.out.println(id);
        return useLocalCache;
    }

    @ApiOperation(value = "获取管理用户分页列表")
    @GetMapping("/getUserList/{page}/{limit}")
    public ResponseResult getUserList(@PathVariable Long page,@PathVariable Long limit,User userQueryVo){
        Page<User> userPage= new Page<>(page, limit);
        userService.selectPage(userPage,userQueryVo);
        remoteWebsocketClient.sendMessage(SpringSecurityBean.userName(),"用户：[" + SpringSecurityBean.userName() + "]查询用户分页列表完毕");
        return ResponseResult.ok().dataPage(userPage.getRecords(),userPage.getTotal());
    }

    @ApiOperation(value = "根据用户id查询用户")
    @GetMapping("/getUserById/{id}")
    public ResponseResult getUser(@PathVariable String id){
        User user=userService.getById(id);
        return ResponseResult.ok().data("user",user);
    }

    @ApiOperation(value = "获取前端User数据 保存user信息")
    @PostMapping("/saveUser")
    public ResponseResult save(@RequestBody User user){
        userService.save(user);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "更新用户信息")
    @PostMapping("/updateUser")
    public ResponseResult updateById(@RequestBody User user){
        userService.updateById(user);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "删除用户")
    @PostMapping("/removeUser/{id}")
    public ResponseResult remove(@PathVariable String id){
        userService.removeById(id);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "批量根据id删除用户")
    @PostMapping("/batchRemoveUser")
    public ResponseResult batchRemove(@RequestBody List<String> idList){
        userService.removeByIds(idList);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "根据用户获取角色数据")
    @GetMapping("/getUserRoleData/{userId}")
    public ResponseResult toAssign(@PathVariable String userId){
        Map<String,Object> roleMap=roleService.findRoleByUserId(userId);
        return ResponseResult.ok().data(roleMap);
    }

    @ApiOperation(value = "根据用户分配角色")
    @PostMapping("/doAssignRole")
    public ResponseResult doAssign(@RequestParam String userId,@RequestParam String [] roleId){
        roleService.saveUserRoleRelationShip(userId,roleId);
        return ResponseResult.ok();
    }



}
