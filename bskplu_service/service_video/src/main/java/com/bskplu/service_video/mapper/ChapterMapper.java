package com.bskplu.service_video.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bskplu.api_video.entity.Chapter;
import feign.Param;

/**
 * @InterfaceName: ChapterMapper
 * @Description: 作品章节接口类
 * @Author BsKPLu
 * @Date 2022/3/9
 * @Version 1.1
 */
public interface ChapterMapper extends BaseMapper<Chapter> {
    Integer getContentOrVideoSortMax(@Param("type") Integer type);
}
