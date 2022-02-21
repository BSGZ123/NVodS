package com.bskplu.api_video.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: ContentVideoVO
 * @Description: 章节下小结 分页封装
 * @Author BsKPLu
 * @Date 2022/2/21
 * @Version 1.1
 */
@Data
public class ContentVideoVO implements Serializable {
    /**
     * id
     */
    private String id;
    /**
     * 小节标题
     */
    private String title;
    /**
     * 小节是否免费
     */
    private Boolean isFree;

    /**
     * 视频源id
     */
    private String videoSourceId;

    /**
     * 持续时间
     */
    private Float duration;
}
