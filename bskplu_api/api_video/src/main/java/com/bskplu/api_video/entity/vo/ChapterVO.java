package com.bskplu.api_video.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ChapterVO
 * @Description: 章节分页封装
 * @Author BsKPLu
 * @Date 2022/2/21
 * @Version 1.1
 */
@EqualsAndHashCode
@Data
public class ChapterVO extends BaseVo{
    private String id;
    private String title;
    private Integer sort;
    /**
     * 章节下,小节信息
     */
    private List<ContentVideoVO> children = new ArrayList<>();
}
