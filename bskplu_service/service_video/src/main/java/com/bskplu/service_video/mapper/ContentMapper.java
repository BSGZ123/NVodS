package com.bskplu.service_video.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bskplu.api_video.entity.Content;
import com.bskplu.api_video.entity.vo.ContentPreviewVo;
import com.bskplu.api_video.entity.vo.ContentWebVO;
import feign.Param;

/**
 * @InterfaceName: ContentMapper
 * @Description: 内容接口类
 * @Author BsKPLu
 * @Date 2022/3/9
 * @Version 1.1
 */
public interface ContentMapper extends BaseMapper<Content> {
    ContentPreviewVo getContentPreviewWhitById(@Param("id") String id);

    ContentWebVO getContentDetailById(String id);
}
