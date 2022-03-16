package com.bskplu.service_video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bskplu.api_video.entity.Author;
import com.bskplu.api_video.entity.Category;
import com.bskplu.api_video.entity.Content;
import com.bskplu.service_video.service.AuthorService;
import com.bskplu.service_video.service.CategoryService;
import com.bskplu.service_video.service.ContentService;
import com.bskplu.service_video.service.IndexPortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: IndexPortalServiceImpl
 * @Description: 首页内容填充服务实现类
 * @Author BsKPLu
 * @Date 2022/3/10
 * @Version 1.1
 */
@Service
public class IndexPortalServiceImpl implements IndexPortalService {
    @Autowired
    private ContentService contentService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private CategoryService categoryService;


    /**
     * 按照id降序排序获取8行记录
     * @return
     */
    @Override
    @Cacheable(value = "index",key = "'getContentIndexList'")
    public List<Content> getContentIndexList() {
        QueryWrapper<Content> wrapper=new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 8");
        List<Content> contentList=contentService.list(wrapper);
        return contentList;
    }

    /**
     * 按照id降序排序获取10行记录
     * @return
     */
    @Override
    @Cacheable(value = "index",key = "'getAuthorIndexList'")
    public List<Author> getAuthorIndexList() {
        QueryWrapper<Author> wrapper=new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 10");
        List<Author> authors=authorService.list(wrapper);
        return authors;
    }

    @Override
    @Cacheable(value = "index",key = "'getCategoryIndexList'")
    public List<Category> getCategoryIndexList() {
        QueryWrapper<Category> wrapper=new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.eq("parent_id","0");
        wrapper.last("limit 8");
        List<Category> categories=categoryService.list(wrapper);
        return categories;
    }
}
