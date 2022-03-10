package com.bskplu.service_video.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.bskplu.service_video.entity.excel.CategoryData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ClassName: CategoryDataListener
 * @Description: 类别监听器（灵活）
 * @Author BsKPLu
 * @Date 2022/3/9
 * @Version 1.1
 */

@Component
@Slf4j
public class CategoryDataListener extends AnalysisEventListener<CategoryData> {
    @Override
    public void invoke(CategoryData categoryData, AnalysisContext analysisContext) {

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
