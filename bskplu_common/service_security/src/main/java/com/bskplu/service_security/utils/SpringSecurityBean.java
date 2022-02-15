package com.bskplu.service_security.utils;

import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @ClassName: SpringSecurityBean
 * @Description: 安全用户信息
 * @Author BsKPLu
 * @Date 2022/2/15
 * @Version 1.1
 */
@Data
public class SpringSecurityBean {
    /**
     * 获取用户
     * @return
     */
    public static Authentication user () {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获得用户名
     * @return {@link String}
     */
    public static String userName () {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
