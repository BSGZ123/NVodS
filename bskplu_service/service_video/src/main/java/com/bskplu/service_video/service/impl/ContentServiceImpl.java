package com.bskplu.service_video.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bskplu.api_video.entity.Content;
import com.bskplu.api_video.entity.vo.ContentVo;
import com.bskplu.api_video.entity.vo.ContentWebVO;
import com.bskplu.common_utils.utils.ResponseResult;
import com.bskplu.service_video.mapper.ContentMapper;
import com.bskplu.service_video.service.ContentService;

import java.util.List;

/**
 * @ClassName: ContentServiceImpl
 * @Description: 作品内容服务实现类
 * @Author BsKPLu
 * @Date 2022/3/10
 * @Version 1.1
 */
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements ContentService {

    @Override
    public String addContentInfo(ContentVo categoryVo) {
        return null;
    }

    @Override
    public ContentVo getContentWhitInfo(String id) {
        return null;
    }

    @Override
    public boolean updateContentInfo(ContentVo categoryVo) {
        return false;
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
