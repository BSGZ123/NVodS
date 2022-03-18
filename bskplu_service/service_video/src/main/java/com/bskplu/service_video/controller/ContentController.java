package com.bskplu.service_video.controller;

import com.bskplu.api_pay.RemotePayOrderClient;
import com.bskplu.api_video.entity.vo.ContentVo;
import com.bskplu.common_utils.utils.ResponseResult;
import com.bskplu.service_video.service.ChapterService;
import com.bskplu.service_video.service.ContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: ContentController
 * @Description: 视频内容控制类
 * @Author BsKPLu
 * @Date 2022/3/16
 * @Version 1.1
 */
@Api(tags = "作品管理")
@RestController
@RequestMapping("/service_video/content")
@RequiredArgsConstructor
public class ContentController {
    private final ContentService contentService;
    private final ChapterService chapterService;
    private final RemotePayOrderClient remotePayOrderClient;

    @ApiOperation(value = "新增作品内容")
    @PostMapping("/addContentInfo")
    public ResponseResult addContentInfo(ContentVo contentVo){
        return ResponseResult.ok().data("contentId",contentService.addContentInfo(contentVo));
    }

    @ApiOperation(value = "根据id获取作品详细内容信息")
    @GetMapping("/getContentWhitInfo/{id}")
    public ResponseResult getContentWhitInfo(@PathVariable String id){
        return ResponseResult.ok().data(contentService.getContentWhitInfo(id));
    }
}
