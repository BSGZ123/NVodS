package com.bskplu.api_video.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName: Category
 * @Description: 类别
 * @Author BsKPLu
 * @Date 2022/2/20
 * @Version 1.1
 */
@ApiModel(value="com-bskplu-service_video-entity-Category")
@Data
@TableName(value = "`video_category`")
public class Category {
    /**
     * 类别ID
     */
    @TableId(value = "id", type = IdType.ID_WORKER_STR)//主键策略
    @ApiModelProperty(value="类别ID")
    private String id;

    /**
     * 类别名称
     */
    @TableField(value = "`title`")
    @ApiModelProperty(value="类别名称")
    private String title;

    /**
     * 父ID
     */
    @TableField(value = "`parent_id`")
    @ApiModelProperty(value="父ID")
    private String parentId;

    /**
     * 排序字段
     */
    @TableField(value = "`sort`")
    @ApiModelProperty(value="排序字段")
    private Integer sort;

    /**
     * 创建时间
     */
    @TableField(value = "`gmt_create`",fill = FieldFill.INSERT)
    @ApiModelProperty(value="创建时间")
    private Date gmtCreate;

    /**
     * 更新时间
     */
    @TableField(value = "`gmt_modified`",fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value="更新时间")
    private Date gmtModified;

    public static final String COL_ID = "id";

    public static final String COL_TITLE = "title";

    public static final String COL_PARENT_ID = "parent_id";

    public static final String COL_SORT = "sort";

    public static final String COL_GMT_CREATE = "gmt_create";

    public static final String COL_GMT_MODIFIED = "gmt_modified";
}