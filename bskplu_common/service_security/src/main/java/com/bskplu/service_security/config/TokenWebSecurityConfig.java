package com.bskplu.service_security.config;

import com.bskplu.common_utils.constant.Constants;
import com.bskplu.service_security.filter.TokenAuthenticationFilter;
import com.bskplu.service_security.filter.TokenLoginFilter;
import com.bskplu.service_security.security.DefaultPasswordEncoder;
import com.bskplu.service_security.security.TokenLogoutHandler;
import com.bskplu.service_security.security.TokenManager;
import com.bskplu.service_security.security.UnauthorizedEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;



/**
 * @ClassName: TokenWebSecurityConfig
 * @Description: springSecurity配置
 * @Author BsKPLu
 * @Date 2022/2/15
 * @Version 1.1
 */

@Configuration
@EnableWebSecurity //启用web安全配置
@EnableGlobalMethodSecurity(prePostEnabled = true)//方法执行前进行验证
public class TokenWebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    private final TokenManager tokenManager;

    private final DefaultPasswordEncoder defaultPasswordEncoder;

    private final RedisTemplate<String, Object> redisTemplate;


    @Autowired
    public TokenWebSecurityConfig(UserDetailsService userDetailsService, TokenManager tokenManager,
                                  DefaultPasswordEncoder defaultPasswordEncoder, RedisTemplate<String, Object> redisTemplate) {
        this.userDetailsService = userDetailsService;
        this.tokenManager = tokenManager;
        this.defaultPasswordEncoder = defaultPasswordEncoder;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 配置设置
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling()
                .authenticationEntryPoint(new UnauthorizedEntryPoint())
                .and().csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and().logout().logoutUrl(Constants.LOGIN_OUT_URL)
                .addLogoutHandler(new TokenLogoutHandler(tokenManager, redisTemplate)).and()
                .addFilter(new TokenLoginFilter(authenticationManager(), tokenManager, redisTemplate))
                .addFilter(new TokenAuthenticationFilter(authenticationManager(), tokenManager, redisTemplate)).httpBasic();
    }

    /**
     * 配置用户实现类和密码生成
     * @param auth
     * @throws Exception
     */
    @Override
    public void configure (AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(defaultPasswordEncoder);
    }

    /**
     * 配置不拦截
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/api/**", "/wspoint/**");

    }
}
