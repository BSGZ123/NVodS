package com.bskplu.service_security.security;

import com.bskplu.common_utils.constant.Constants;
import com.bskplu.common_utils.utils.ResponseResult;
import com.bskplu.common_utils.utils.ResponseUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: TokenLogoutHandler
 * @Description: 令牌注销 用户登出处理
 * @Author BsKPLu
 * @Date 2022/2/15
 * @Version 1.1
 */
public class TokenLogoutHandler implements LogoutHandler {

    private TokenManager tokenManager;
    private RedisTemplate redisTemplate;

    public TokenLogoutHandler(TokenManager tokenManager,RedisTemplate redisTemplate){
        this.tokenManager=tokenManager;
        this.redisTemplate=redisTemplate;
    }



    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
        String token = httpServletRequest.getHeader(Constants.TOKEN);
        if (token != null) {
            /*tokenManager.removeToken(token);*/
            //清空当前用户缓存中的权限数据
            String userName = tokenManager.getUserFromToken(token);
            redisTemplate.delete(userName);
        }
        ResponseUtil.out(httpServletResponse, ResponseResult.ok());
    }
}
