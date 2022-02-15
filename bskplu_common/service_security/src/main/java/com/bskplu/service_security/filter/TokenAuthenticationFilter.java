package com.bskplu.service_security.filter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * @ClassName: TokenAuthenticationFilter
 * @Description: 登录授权验证拦截器
 * @Author BsKPLu
 * @Date 2022/2/15
 * @Version 1.1
 */
public class TokenAuthenticationFilter extends BasicAuthenticationFilter {

    public TokenAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }
}
