package com.bskplu.service_security.filter;

import com.bskplu.common_utils.constant.Constants;
import com.bskplu.common_utils.utils.ResponseResult;
import com.bskplu.common_utils.utils.ResponseUtil;
import com.bskplu.service_security.entity.SecurityUser;
import com.bskplu.service_security.entity.User;
import com.bskplu.service_security.security.TokenManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: TokenLoginFilter
 * @Description: 系统登录认证过滤器->令牌验证过滤器 对用户密码进行校验
 * 继承UsernamePasswordAuthenticationFilter
 * @Author BsKPLu
 * @Date 2022/2/15
 * @Version 1.1
 */
public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {

    //身份验证管理器
    private final AuthenticationManager authenticationManager;
    private final TokenManager tokenManager;
    private final RedisTemplate<String,Object> redisTemplate;


    /**
     * 登录令牌过滤器
     * @param authenticationManager
     * @param tokenManager
     * @param redisTemplate
     */
    public TokenLoginFilter(AuthenticationManager authenticationManager, TokenManager tokenManager, RedisTemplate<String, Object> redisTemplate) {
        this.authenticationManager = authenticationManager;
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
        this.setPostOnly(false);
        //设定身份验证适配器（访问路径）
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(Constants.LOGIN_URL,"POST"));
    }

    /**
     * 重写 尝试身份验证逻辑
     * @param req
     * @param res
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException {
        try {
            User user = new ObjectMapper().readValue(req.getInputStream(), User.class);
            System.out.println(user.getUsername());
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 重写 登录成功
     * @param req
     * @param res
     * @param chain
     * @param auth
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
                                            Authentication auth) {
        SecurityUser user = (SecurityUser) auth.getPrincipal();
        String token = tokenManager.createToken(user.getCurrentUserInfo().getUsername());
        // 将登陆成功的用户信息保存到缓存
        redisTemplate.opsForValue().set(user.getCurrentUserInfo().getUsername(), user.getPermissionValueList());
        // 用于判断是否多人登陆
        redisTemplate.opsForValue().set(Constants.LOGIN_TOKEN_KEY + user.getCurrentUserInfo().getUsername(), token, Constants.EXPIRATION_TIME_SECONDS, TimeUnit.SECONDS);
        ResponseUtil.out(res, ResponseResult.ok().data("token", token));
    }

    /**
     * 重写 登录失败了
     * @param request
     * @param response
     * @param e
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException e) {
        ResponseUtil.out(response, ResponseResult.error("用户名或密码错误"));
    }




}
