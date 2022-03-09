package com.bskplu.service_video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bskplu.api_video.entity.Category;
import com.bskplu.api_video.entity.category.LevelCategory;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @InterfaceName: CategoryService
 * @Description: 分类服务接口
 * @Author BsKPLu
 * @Date 2022/3/9
 * @Version 1.1
 */
public interface CategoryService extends IService<Category> {
    void saveCategory(MultipartFile file);

    List<LevelCategory> getTreeCategory();
}
