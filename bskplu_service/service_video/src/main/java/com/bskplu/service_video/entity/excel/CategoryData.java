package com.bskplu.service_video.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @ClassName: CategoryData
 * @Description: 类别数据类
 * @Author BsKPLu
 * @Date 2022/2/16
 * @Version 1.1
 */
@Data
public class CategoryData {

    @ExcelProperty(index = 0)
    private String oneCategoryData;
    @ExcelProperty(index = 1)
    private String twoCategoryData;
}
