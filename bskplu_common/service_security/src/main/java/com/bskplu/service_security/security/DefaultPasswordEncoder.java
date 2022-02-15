package com.bskplu.service_security.security;

import com.bskplu.common_utils.utils.MD5;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @ClassName: DefaultPasswordEncoder
 * @Description: 密码的几种处理办法
 * @Author BsKPLu
 * @Date 2022/2/15
 * @Version 1.1
 */
@Component
public class DefaultPasswordEncoder implements PasswordEncoder {

    public DefaultPasswordEncoder(){
        this(-1);
    }

    public DefaultPasswordEncoder(int strength){}


    /**
     * 编码
     * @param rawPassword 原始密码
     * @return
     */
    @Override
    public String encode(CharSequence rawPassword) {
        return MD5.encrypt(rawPassword.toString());
    }

    /**
     * 匹配
     * @param rawPassword 原始密码
     * @param encodedPassword 编码过的密码
     * @return
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(MD5.encrypt(rawPassword.toString()));
    }
}
