package com.bskplu.api_video.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @ClassName: BaseVo
 * @Description: 分页封装基础类
 * @Author BsKPLu
 * @Date 2022/2/21
 * @Version 1.1
 */
@Data
public class BaseVo implements Serializable {

    @ApiModelProperty(name = "当前页数")
    private Integer page;
    @ApiModelProperty(name = "每页记录数")
    private Integer limit;
    @ApiModelProperty(name = "其他参数")
    private Map<String, Object> params;


}
