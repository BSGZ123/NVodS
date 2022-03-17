package com.bskplu.service_video.controller;

import com.bskplu.common_utils.utils.ResponseResult;
import com.bskplu.service_video.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName: CategoryController
 * @Description: 类别控制类
 * @Author BsKPLu
 * @Date 2022/3/16
 * @Version 1.1
 */
@Api(tags = "分类管理")
@RestController
@Slf4j
@RequestMapping("/service_video/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "获取所有分类信息")
    @GetMapping("/getAllCategory")
    public ResponseResult getAllCategory(){
        return ResponseResult.ok().data(categoryService.getTreeCategory());
    }


    @ApiOperation(value = "导入新增分类信息")
    @PostMapping("/addCategory")
    public ResponseResult addCategory(MultipartFile multipartFile){
        categoryService.saveCategory(multipartFile);
        return ResponseResult.ok();
    }


}
