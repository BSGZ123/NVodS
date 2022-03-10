package com.bskplu.service_video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bskplu.api_video.entity.Chapter;
import com.bskplu.api_video.entity.ContentVideo;
import com.bskplu.api_video.entity.vo.ChapterVO;
import com.bskplu.service_video.mapper.ChapterMapper;
import com.bskplu.service_video.service.ChapterService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: ChapterServiceImpl
 * @Description: 作品章节服务实现类
 * @Author BsKPLu
 * @Date 2022/3/10
 * @Version 1.1
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {


    @Override
    public List<ChapterVO> getChapterContentVideo(String contentId) {
        final LambdaQueryWrapper<Chapter> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Chapter::getContentId,contentId).orderByAsc(Chapter::getSort);
        final List<Chapter> chapters =baseMapper.selectList(queryWrapper);

        //根据课程id 获取视频内容章节
        final LambdaQueryWrapper<ContentVideo> contentVideoLambdaQueryWrapper=new LambdaQueryWrapper<>();
        contentVideoLambdaQueryWrapper.eq(ContentVideo::getContentId,contentId);
        //final List<ContentVideo> contentVideos=
        return null;
    }

    @Override
    public Boolean deleteChapterById(String id) {
        return null;
    }

    @Override
    public Integer getContentOrVideoSortMax(Integer type) {
        return null;
    }

    @Override
    public Boolean deleteChapterByContentId(String id) {
        return null;
    }
}
