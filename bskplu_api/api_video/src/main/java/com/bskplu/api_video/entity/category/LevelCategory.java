package com.bskplu.api_video.entity.category;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: LevelCategory
 * @Description: 作品分级
 * @Author BsKPLu
 * @Date 2022/2/21
 * @Version 1.1
 */
@Data
public class LevelCategory {
    /**
     * id
     */
    private String id;
    /**
     * 标题
     */
    private String title;
    /**
     * 孩子们
     */
    private List<LevelCategory> children = new ArrayList<>();
}
