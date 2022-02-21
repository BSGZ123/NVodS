package com.bskplu.api_video.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName: AuthorQuery
 * @Description: 认证封装
 * @Author BsKPLu
 * @Date 2022/2/21
 * @Version 1.1
 */
@EqualsAndHashCode
@Data
public class AuthorQuery extends BaseVo{
    private String name;
    private Integer level;
    private String begin;
    private String end;
}
