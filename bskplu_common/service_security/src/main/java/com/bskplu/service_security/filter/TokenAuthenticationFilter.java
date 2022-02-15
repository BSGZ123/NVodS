package com.bskplu.service_security.filter;

import com.bskplu.common_utils.constant.Constants;
import com.bskplu.common_utils.utils.ResponseResult;
import com.bskplu.common_utils.utils.ResponseUtil;
import com.bskplu.service_security.security.TokenManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName: TokenAuthenticationFilter
 * @Description: 登录授权验证拦截校验
 * @Author BsKPLu
 * @Date 2022/2/15
 * @Version 1.1
 */
public class TokenAuthenticationFilter extends BasicAuthenticationFilter {
    private final TokenManager tokenManager;
    private final RedisTemplate<String, Object> redisTemplate;

    public TokenAuthenticationFilter(AuthenticationManager authManager, TokenManager tokenManager, RedisTemplate<String, Object> redisTemplate) {
        super(authManager);
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
    }

    /**
     * token验证逻辑
     * @param req
     * @param res
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        // 判断为登陆放行
        if (Constants.LOGIN_URL.contains(req.getRequestURI())) {
            chain.doFilter(req, res);
            return;
        }
        // 根据token信息判断是否有效
        UsernamePasswordAuthenticationToken authentication = null;
        try {
            authentication = getAuthentication(req);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseUtil.out(res, ResponseResult.error("令牌失效请重新登陆"));
        }

        if (authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            ResponseUtil.out(res, ResponseResult.error("令牌失效请重新登陆"));
        }
        chain.doFilter(req, res);
    }


    /**
     * 授权逻辑
     * @param request
     * @return UsernamePasswordAuthenticationToken
     */
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        // token置于header里
        String token = request.getHeader(Constants.TOKEN);
        if (token != null && !"".equals(token.trim())) {
            String userName = tokenManager.getUserFromToken(token);
            // 构建权限标识 放入security当中
            List<String> permissionValueList = (List<String>) redisTemplate.opsForValue().get(userName);
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            assert permissionValueList != null;
            if (permissionValueList.size() > 0) {
                for (String permissionValue : permissionValueList) {
                    if (StringUtils.isEmpty(permissionValue)) {
                        continue;
                    }
                    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permissionValue);
                    authorities.add(authority);
                }
            }

            if (!StringUtils.isEmpty(userName)) {
                return new UsernamePasswordAuthenticationToken(userName, token, authorities);
            }
            return null;
        }
        return null;
    }
}
