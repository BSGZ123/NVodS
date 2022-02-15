package com.bskplu.service_security.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: User
 * @Description: 用户登录实体类
 * @Author BsKPLu
 * @Date 2022/2/15
 * @Version 1.1
 */
@Data
@ApiModel(description = "用户实体类")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "微信id")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "用户头像")
    private String salt;

    @ApiModelProperty(value = "用户token")
    private String token;

}
