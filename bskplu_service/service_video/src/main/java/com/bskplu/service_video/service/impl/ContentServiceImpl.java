package com.bskplu.service_video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bskplu.api_video.entity.Content;
import com.bskplu.api_video.entity.ContentDescription;
import com.bskplu.api_video.entity.vo.ContentVo;
import com.bskplu.api_video.entity.vo.ContentWebVO;
import com.bskplu.common_utils.constant.Constants;
import com.bskplu.common_utils.utils.ResponseResult;
import com.bskplu.service_base.utils.text.StringUtils;
import com.bskplu.service_video.mapper.ContentMapper;
import com.bskplu.service_video.service.ChapterService;
import com.bskplu.service_video.service.ContentDescriptionService;
import com.bskplu.service_video.service.ContentService;
import com.bskplu.service_video.service.ContentVideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.querydsl.QuerydslRepositoryInvokerAdapter;
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
        return ResponseResult.ok().data(this.baseMapper.getContentPreviewWhitById(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult sendContent(String id) {
        final Content content=new Content();
        content.setId(id);
        content.setStatus(Constants.CONTENT_STATUS_NORMAL);
        return ResponseResult.toOk(this.updateById(content));
    }

    @Override
    public ResponseResult getContentListPage(ContentVo contentVo) {
        final Page<Content> contentPage=new Page<>(contentVo.getPage(),contentVo.getLimit());
        final LambdaQueryWrapper<Content> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.isNoneBlank(contentVo.getCategoryParentId()), Content::getCategoryParentId, contentVo.getCategoryParentId())
                .eq(StringUtils.isNoneBlank(contentVo.getCategoryId()),Content::getCategoryId,contentVo.getCategoryId())
                .eq(StringUtils.isNoneBlank(contentVo.getAuthorId()),Content::getAuthorId,contentVo.getAuthorId())
                .like(StringUtils.isNoneBlank(contentVo.getTitle()),Content::getTitle,contentVo.getTitle())
                .eq(Content::getIsDeleted,0);

        if (contentVo.getType()!=null){
            if (contentVo.getType()==1){
                wrapper.orderByDesc(Content::getBuyCount);
            }
            if (contentVo.getType()==2){
                wrapper.orderByDesc(Content::getGmtCreate);
            }
            if (contentVo.getType()==3){
                wrapper.orderByDesc(Content::getPrice);
            }
        }
        this.baseMapper.selectPage(contentPage,wrapper);
        return ResponseResult.ok().dataPage(contentPage.getRecords(),contentPage.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult deleteContentById(String id) {
        //删除关联的小结和视频
        this.contentVideoService.deleteContentVideoWithByContentId(id);
        //删除关联的章节
        this.chapterService.deleteChapterByContentId(id);
        //删除作品
        final int i=this.baseMapper.deleteById(id);
        return ResponseResult.toOk(i>0);
    }

    @Override
    public List<Content> selectByAuthorId(String id) {
        //这里需要注意一下
        QueryWrapper<Content> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("author_id",id);
        //根据最后更新时间倒序排列
        queryWrapper.orderByDesc("gmt_modified");
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    /**
     * 获取作品详情和更新播放信息
     */
    public ContentWebVO selectContentDetailById(String contentId) {
        this.updatePageViewCount(contentId);
        return baseMapper.getContentDetailById(contentId);
    }

    /**
     * 更新播放信息
     * @param contentId
     * @return
     */
    @Override
    public ResponseResult updatePageViewCount(String contentId) {
        Content content=baseMapper.selectById(contentId);
        content.setViewCount(content.getViewCount()+1);
        baseMapper.updateById(content);
        return ResponseResult.ok();
    }
}
