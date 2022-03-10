package com.bskplu.service_video.service.impl;

import com.bskplu.api_video.entity.Author;
import com.bskplu.api_video.entity.Category;
import com.bskplu.api_video.entity.Content;
import com.bskplu.service_video.service.IndexPortalService;
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
    @Override
    public List<Content> getContentIndexList() {
        return null;
    }

    @Override
    public List<Author> getAuthorIndexList() {
        return null;
    }

    @Override
    public List<Category> getCategoryIndexList() {
        return null;
    }
}
