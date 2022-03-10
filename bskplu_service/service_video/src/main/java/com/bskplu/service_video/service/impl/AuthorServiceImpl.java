package com.bskplu.service_video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bskplu.api_video.entity.Author;
import com.bskplu.api_video.entity.vo.AuthorQuery;
import com.bskplu.service_video.mapper.AuthorMapper;
import com.bskplu.service_video.service.AuthorService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @ClassName: AuthorServiceImpl
 * @Description: 创作者服务实现类
 * @Author BsKPLu
 * @Date 2022/3/9
 * @Version 1.1
 */
@Service
public class AuthorServiceImpl extends ServiceImpl<AuthorMapper, Author> implements AuthorService {
    @Override
    public void pageQuery(Page<Author> pageInfo, AuthorQuery authorQuery) {
        QueryWrapper<Author> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_modified");
        queryWrapper.orderByAsc("sort");

        if(authorQuery==null){
            baseMapper.selectPage(pageInfo,queryWrapper);
        }

        //判断条件是否为空
        assert authorQuery != null;
        if(!StringUtils.isEmpty(authorQuery.getName())){
            queryWrapper.like("name",authorQuery.getName());
        }

        if(!StringUtils.isEmpty(authorQuery.getLevel())){
            queryWrapper.eq("level",authorQuery.getLevel());
        }
        //查询创建时间是否大于等于开始时间
        if (!StringUtils.isEmpty(authorQuery.getBegin())){
            queryWrapper.ge("gmt_create",authorQuery.getBegin());
        }

        //查询创建时间小于等于结束时间
        if (!StringUtils.isEmpty(authorQuery.getEnd())){
            queryWrapper.le("gmt_create",authorQuery.getEnd());
        }

        baseMapper.selectPage(pageInfo,queryWrapper);


    }
}
