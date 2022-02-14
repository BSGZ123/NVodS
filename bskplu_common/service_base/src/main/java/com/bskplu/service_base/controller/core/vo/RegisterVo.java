package com.bskplu.service_base.controller.core.vo;

import lombok.Data;

/**
 * @ClassName: RegisterVo
 * @Description: 创建用户注册接口用于数据封装
 * @Author BsKPLu
 * @Date 2022/2/14
 * @Version 1.1
 */
@Data
public class RegisterVo {
    private String nickname;
    private String phone;
    private String password;
    private String code;//验证码
}
