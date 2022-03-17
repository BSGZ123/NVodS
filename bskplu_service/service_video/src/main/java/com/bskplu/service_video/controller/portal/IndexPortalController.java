package com.bskplu.service_video.controller.portal;

import com.bskplu.common_utils.utils.ResponseResult;
import com.bskplu.service_video.service.IndexPortalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: IndexPortalController
 * @Description: 前端门户页面数据 获取视频播放 索引门户
 * @Author BsKPLu
 * @Date 2022/3/16
 * @Version 1.1
 */

@RestController
@Slf4j
@RequestMapping("/service_video/indexPortal")
@RequiredArgsConstructor
public class IndexPortalController {

    private final IndexPortalService indexPortalService;

    /**
     * 加载门户首页数据
     * @return
     */
    @GetMapping("/getIndexData")
    public ResponseResult getIndexData(){
        return ResponseResult.ok().data("contentList",indexPortalService.getContentIndexList())
                .data("authorList",indexPortalService.getAuthorIndexList())
                .data("categoryList",indexPortalService.getCategoryIndexList());
    }

}
