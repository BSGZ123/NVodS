package com.bskplu.service_video.service;

import com.bskplu.api_video.entity.Author;
import com.bskplu.api_video.entity.Category;
import com.bskplu.api_video.entity.Content;

import java.util.List;

/**
 * @InterfaceName: IndexPortalService
 * @Description: 为首页内容展示服务接口
 * @Author BsKPLu
 * @Date 2022/3/9
 * @Version 1.1
 */
public interface IndexPortalService {
    /**
     * 视频作品数据
     * @return
     */
    List<Content> getContentIndexList();

    /**
     * 视频作者数据
     * @return
     */
    List<Author> getAuthorIndexList();

    /**
     * 视频分类数据
     * @return
     */
    List<Category> getCategoryIndexList();
}
