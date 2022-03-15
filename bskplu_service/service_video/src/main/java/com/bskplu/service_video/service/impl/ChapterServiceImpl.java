package com.bskplu.service_video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bskplu.api_video.entity.Chapter;
import com.bskplu.api_video.entity.ContentVideo;
import com.bskplu.api_video.entity.vo.ChapterVO;
import com.bskplu.api_video.entity.vo.ContentVideoVO;
import com.bskplu.common_utils.utils.ResultCode;
import com.bskplu.service_base.exception.BusinessException;
import com.bskplu.service_video.mapper.ChapterMapper;
import com.bskplu.service_video.service.ChapterService;
import com.bskplu.service_video.service.ContentVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


    @Autowired
    private ContentVideoService contentVideoService;

    @Override
    public List<ChapterVO> getChapterContentVideo(String contentId) {
        final LambdaQueryWrapper<Chapter> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Chapter::getContentId,contentId).orderByAsc(Chapter::getSort);
        final List<Chapter> chapters =baseMapper.selectList(queryWrapper);

        //根据课程id 获取视频内容章节
        final LambdaQueryWrapper<ContentVideo> contentVideoLambdaQueryWrapper=new LambdaQueryWrapper<>();
        contentVideoLambdaQueryWrapper.eq(ContentVideo::getContentId,contentId);
        final List<ContentVideo> contentVideos=contentVideoService.list(contentVideoLambdaQueryWrapper);

        //组装章节数据
        final ArrayList<ChapterVO> list=new ArrayList<ChapterVO>(16);

        //组装数据
        for (Chapter chapter:chapters){
            final ChapterVO chapterVO=new ChapterVO();
            BeanUtils.copyProperties(chapter,chapterVO);

            //小节
            final ArrayList<ContentVideoVO> a1=new ArrayList<ContentVideoVO>();
            for (ContentVideo video:contentVideos){
                if (video.getChapterId().equals(chapterVO.getId())){
                    ContentVideoVO vo=new ContentVideoVO();
                    BeanUtils.copyProperties(video,vo);
                    a1.add(vo);
                }
            }
            chapterVO.setChildren(a1);
            list.add(chapterVO);
        }
        return list;
    }

    @Override
    public Boolean deleteChapterById(String id) {
        if (contentVideoService.getCountByChapterId(id)){
            throw new BusinessException(ResultCode.ERROR,"该分章节下存在小节，请先删除小节");
        }
        Integer result=baseMapper.deleteById(id);
        return result!=null && result>0;
    }

    @Override
    public Integer getContentOrVideoSortMax(Integer type) {
        return this.baseMapper.getContentOrVideoSortMax(type);
    }

    @Override
    public Boolean deleteChapterByContentId(String id) {
        final LambdaQueryWrapper<Chapter> wrapper=new LambdaQueryWrapper<>();
        this.baseMapper.delete(wrapper.eq(Chapter::getContentId,id));
        return false;
    }
}
