package com.bskplu.api_video.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName: ContentPreviewVo
 * @Description: 作品预览 信息分页封装
 * @Author BsKPLu
 * @Date 2022/2/21
 * @Version 1.1
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ContentPreviewVo extends BaseVo{
    /**
     * 标题
     */
    private String title;
    /**
     * 封面
     */
    private String cover;
    /**
     * 内容num
     */
    private Integer contentNum;
    /**
     * 一个类别
     */
    private String oneCategory;
    /**
     * 两个类别
     */
    private String twoCategory;
    /**
     * 作者
     */
    private String author;
    /**
     * 价格
     */
    private String price;
}
