package com.bskplu.service_user.controller;

import com.bskplu.api_user.RemoteUserClient;
import com.bskplu.api_user.entity.User;
import com.bskplu.common_utils.utils.JwtUtils;
import com.bskplu.common_utils.utils.ResponseResult;
import com.bskplu.service_base.controller.core.vo.LoginVo;
import com.bskplu.service_base.controller.core.vo.RegisterVo;
import com.bskplu.service_base.exception.BusinessException;
import com.bskplu.service_user.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: UserController
 * @Description: 门户系统 用户登录处理
 * @Author BsKPLu
 * @Date 2022/2/17
 * @Version 1.1
 */
@RestController
@RequestMapping("/service_user/user")
@RequiredArgsConstructor
public class UserController implements RemoteUserClient {
    private final UserService userService;

    @ApiOperation(value = "门户用户登录")
    @PostMapping("/login")
    public ResponseResult login(@RequestBody LoginVo loginVo){
        String token=userService.login(loginVo);
        return ResponseResult.ok().data("token",token);

    }

    @ApiOperation(value = "门户用户注册")
    @PostMapping("/register")
    public ResponseResult register(@RequestBody RegisterVo registerVo){
        userService.register(registerVo);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "根据token信息获取登录信息")
    @GetMapping("auth/getLoginInfo")
    public ResponseResult getLoginInfo(HttpServletRequest request){
        try{
            String userId= JwtUtils.getUserIdByJwtToken(request);
            //依据id查询数据库获取用户信息
            User user=userService.getById(userId);
            return ResponseResult.ok().data("userInfo",user);
        }catch (Exception e){
            e.printStackTrace();
            throw new BusinessException("获取用户信息失败");
        }

    }
    @Override
    @ApiOperation(value = "根据id获取用户信息")
    @GetMapping("getUserInfoOrderById/{id}")
    public User getLoginInfo(@PathVariable String id) {
        return this.userService.getById(id);
    }
}
