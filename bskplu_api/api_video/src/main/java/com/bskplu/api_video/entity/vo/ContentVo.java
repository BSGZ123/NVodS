package com.bskplu.api_video.entity.vo;

import io.swagger.annotations.Api;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @ClassName: ContentVo
 * @Description: 作品封装
 * @Author BsKPLu
 * @Date 2022/2/21
 * @Version 1.1
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Api(tags = "作品前端接收类")
public class ContentVo extends BaseVo{
    /**
     * id
     */
    private String id;

    /**
     * 标题
     */
    private String title;
    /**
     * 分类ID
     */
    private String categoryId;
    /**
     * 父级分类ID
     */
    private String categoryParentId;
    /**
     * 作者id
     */
    private String authorId;
    /**
     * 总视频数
     */
    private Integer contentNum;
    /**
     * 简介
     */
    private String description;
    /**
     * 封面
     */
    private String cover;
    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 类型 1 销量排序  2 最新排序 3 价格排序
     */
    private Integer type;
}
