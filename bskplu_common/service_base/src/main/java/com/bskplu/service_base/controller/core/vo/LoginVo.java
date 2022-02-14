package com.bskplu.service_base.controller.core.vo;

import lombok.Data;

/**
 * @ClassName: LoginVo
 * @Description: 创建用户登录接口用于数据封装
 * @Author BsKPLu
 * @Date 2022/2/14
 * @Version 1.1
 */
@Data
public class LoginVo {
    private String phone;
    private String password;
}
