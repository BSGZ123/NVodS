package com.bskplu.service_authority.security;

import com.bskplu.service_authority.entity.User;
import com.bskplu.service_authority.service.MenuService;
import com.bskplu.service_authority.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.bskplu.service_security.entity.SecurityUser;

import java.util.List;

/**
 * @ClassName: UserDetailsServiceImpl
 * @Description: 重写用户实现类 实现UserDetailsService接口
 * @Author BsKPLu
 * @Date 2022/3/4
 * @Version 1.1
 */
@Service("userDetailsService")
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;
    private final MenuService menuService;


    /**
     * 根据账号获取用户信息
     * @param s
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user=userService.selectByUsername(s);
        if(user==null){
            throw new UsernameNotFoundException("用户名或密码不正确!");
        }else {
            com.bskplu.service_security.entity.User secUser=new com.bskplu.service_security.entity.User();
            BeanUtils.copyProperties(user,secUser);
            List<String> authorities = menuService.selectPermissionValueByUserId(user.getId());
            SecurityUser securityUser = new SecurityUser(secUser);
            securityUser.setPermissionValueList(authorities);
            return securityUser;
        }
    }
}
