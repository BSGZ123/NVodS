package com.bskplu.service_video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bskplu.api_video.entity.Chapter;
import com.bskplu.api_video.entity.vo.ChapterVO;

import java.util.List;

/**
 * @InterfaceName: ChapterService
 * @Description: 章节服务接口类
 * @Author BsKPLu
 * @Date 2022/3/9
 * @Version 1.1
 */
public interface ChapterService extends IService<Chapter> {
    List<ChapterVO> getChapterContentVideo(String contentId);

    Boolean deleteChapterById(String id);

    Integer getContentOrVideoSortMax(Integer type);

    Boolean deleteChapterByContentId(String id);

}
