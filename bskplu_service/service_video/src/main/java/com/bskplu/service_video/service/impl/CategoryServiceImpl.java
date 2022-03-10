package com.bskplu.service_video.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bskplu.api_video.entity.Category;
import com.bskplu.api_video.entity.category.LevelCategory;
import com.bskplu.service_video.entity.excel.CategoryData;
import com.bskplu.service_video.listener.CategoryDataListener;
import com.bskplu.service_video.mapper.CategoryMapper;
import com.bskplu.service_video.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: CategoryServiceImpl
 * @Description: 分类查询服务类
 * @Author BsKPLu
 * @Date 2022/3/10
 * @Version 1.1
 */
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private CategoryDataListener categoryDataListener;

    @Override
    public void saveCategory(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(), CategoryData.class,categoryDataListener).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<LevelCategory> getTreeCategory() {
        //读取一级分类
        QueryWrapper<Category> oneQueryWrapper =new QueryWrapper<>();
        oneQueryWrapper.eq("parent_id",0);
        List<Category> oneCategories=baseMapper.selectList(oneQueryWrapper);

        //根据一级分类读取二级分类
        QueryWrapper<Category> twoQueryWrapper=new QueryWrapper<>();
        oneQueryWrapper.ne("parent_id",0);
        List<Category> twoCategories=baseMapper.selectList(twoQueryWrapper);

        //封装模型数据
        List<LevelCategory> reList=new ArrayList<>();
        for(Category oneCategory:oneCategories ){

        }
        return null;
    }
}
