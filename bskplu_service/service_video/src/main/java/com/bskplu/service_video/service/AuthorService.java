package com.bskplu.service_video.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bskplu.api_video.entity.Author;
import com.bskplu.api_video.entity.vo.AuthorQuery;

/**
 * @InterfaceName: AuthorService
 * @Description: 创作者视频服务接口
 * @Author BsKPLu
 * @Date 2022/3/9
 * @Version 1.1
 */
public interface AuthorService extends IService<Author> {
    /**
     * 创作者分页查询
     * @param pageInfo
     * @param authorQuery
     */
    void pageQuery (Page<Author> pageInfo, AuthorQuery authorQuery);
}
