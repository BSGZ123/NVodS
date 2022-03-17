package com.bskplu.service_video.controller;

import com.bskplu.service_video.service.CategoryService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
