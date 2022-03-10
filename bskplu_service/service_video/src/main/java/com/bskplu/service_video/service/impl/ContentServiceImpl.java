package com.bskplu.service_video.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bskplu.api_video.entity.Content;
import com.bskplu.api_video.entity.ContentDescription;
import com.bskplu.api_video.entity.vo.ContentVo;
import com.bskplu.api_video.entity.vo.ContentWebVO;
import com.bskplu.common_utils.utils.ResponseResult;
import com.bskplu.service_video.mapper.ContentMapper;
import com.bskplu.service_video.service.ChapterService;
import com.bskplu.service_video.service.ContentDescriptionService;
import com.bskplu.service_video.service.ContentService;
import com.bskplu.service_video.service.ContentVideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: ContentServiceImpl
 * @Description: 作品内容服务实现类
 * @Author BsKPLu
 * @Date 2022/3/10
 * @Version 1.1
 */
@Service
@RequiredArgsConstructor
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements ContentService {

    private final ContentDescriptionService contentDescriptionService;
    private final ContentVideoService contentVideoService;
    private final ChapterService chapterService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String addContentInfo(ContentVo categoryVo) {
        final Content content=new Content();
        //从前端传递的封装模型中取出适合Content的数据
        BeanUtils.copyProperties(categoryVo,content);
        baseMapper.insert(content);
        final ContentDescription contentDescription=new ContentDescription();
        //从前端传递的封装模型中获取内容详情
        contentDescription.setId(content.getId());
        contentDescription.setDescription(categoryVo.getDescription());
        contentDescriptionService.save(contentDescription);
        return content.getId();
    }

    @Override
    public ContentVo getContentWhitInfo(String id) {
        ContentVo contentVo=new ContentVo();
        Content byId=baseMapper.selectById(id);
        BeanUtils.copyProperties(byId,contentVo);
        ContentDescription description=contentDescriptionService.getById(id);
        if(description!=null){
            contentVo.setDescription(description.getDescription());
        }
        return contentVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateContentInfo(ContentVo contentVo) {
        final Content content=new Content();
        BeanUtils.copyProperties(contentVo,content);
        final  ContentDescription contentById=contentDescriptionService.getById(content.getId());
        contentById.setDescription(contentVo.getDescription());
        contentDescriptionService.updateById(contentById);
        return baseMapper.updateById(content)>0;
    }

    @Override
    public ResponseResult getContentPreview(String id) {
        return null;
    }

    @Override
    public ResponseResult sendContent(String id) {
        return null;
    }

    @Override
    public ResponseResult getContentListPage(ContentVo categoryVo) {
        return null;
    }

    @Override
    public ResponseResult deleteContentById(String id) {
        return null;
    }

    @Override
    public List<Content> selectByAuthorId(String id) {
        return null;
    }

    @Override
    public ContentWebVO selectContentDetailById(String contentId) {
        return null;
    }

    @Override
    public ResponseResult updatePageViewCount(String contentId) {
        return null;
    }
}
