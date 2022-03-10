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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName: CategoryServiceImpl
 * @Description: 分类查询服务类
 * @Author BsKPLu
 * @Date 2022/3/10
 * @Version 1.1
 */
@Service
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
            //把category转换成LevelCategory
            LevelCategory oneLevelCategory =new LevelCategory();

            //属性的复制 把被复制的对象属性一一对应复制到新的对象中 原对象没有的属性就不复制
            BeanUtils.copyProperties(oneCategory,oneLevelCategory);

            //嵌套循环 获取子类（遍历所有二级子类）
            for (Category twoCategory: twoCategories){
                //如果二级子类的parentId与一级的parentId相等，则可知当前二级子类就是一级的子类
                if(twoCategory.getParentId().equals(oneCategory.getParentId())){
                    LevelCategory twoLevelCategory=new LevelCategory();
                    BeanUtils.copyProperties(twoCategory,twoLevelCategory);
                    oneLevelCategory.getChildren().add(twoLevelCategory);
                }
            }
            reList.add(oneLevelCategory);
        }
        Collections.reverse(reList);
        return reList;
    }
}
