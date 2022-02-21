package com.bskplu.api_video.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: ContentVideoInfoVO
 * @Description: 视频内容信息 分页封装
 * @Author BsKPLu
 * @Date 2022/2/21
 * @Version 1.1
 */
@Data
public class ContentVideoInfoVO implements Serializable {
    @ApiModelProperty(value = "视频ID")
    private String id;

    @ApiModelProperty(value = "小节名称")
    private String title;

    @ApiModelProperty(value = "作品ID")
    private String contentId;

    @ApiModelProperty(value = "章节ID")
    private String chapterId;

    @ApiModelProperty(value = "视频资源")
    private String videoSourceId;

    @ApiModelProperty(value = "显示排序")
    private Integer sort;

    @ApiModelProperty(value = "是否可以试听：0默认 1免费")
    private Boolean isFree;

    @ApiModelProperty(value = "视频标题")
    private String videoOriginalName;
}
