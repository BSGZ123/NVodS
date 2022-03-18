package com.bskplu.service_video.controller;

import com.bskplu.api_video.entity.ContentVideo;
import com.bskplu.api_video.entity.vo.ContentVideoInfoVO;
import com.bskplu.api_vod.VodClient;
import com.bskplu.common_utils.utils.ResponseResult;
import com.bskplu.service_video.service.ContentService;
import com.bskplu.service_video.service.ContentVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: ContentVideoController
 * @Description: 视频内容控制类
 * @Author BsKPLu
 * @Date 2022/3/16
 * @Version 1.1
 */
@Api(tags = "小节管理")
@RestController
@RequestMapping("/service_video/contentVideo")
@RequiredArgsConstructor
public class ContentVideoController {
    private final ContentVideoService contentVideoService;
    private final VodClient vodClient;

    @ApiOperation(value = "新增小节")
    @PostMapping
    public ResponseResult addContentVideo(@RequestBody ContentVideoInfoVO contentVideoInfoVO){
        return ResponseResult.toOk(contentVideoService.addContentVideo(contentVideoInfoVO));
    }

    @ApiOperation(value = "更新视频内容")
    @PostMapping
    public ResponseResult updateContentVideo(@RequestBody ContentVideoInfoVO contentVideoInfoVO){
        return ResponseResult.toOk(contentVideoService.updateContentVideo(contentVideoInfoVO));
    }

    @ApiOperation(value = "根据id获得视频内容信息")
    @GetMapping("{id}")
    public ResponseResult getContentVideoById(@PathVariable String id){
        final ContentVideo contentVideoId=contentVideoService.getById(id);
        final ContentVideoInfoVO contentVideoInfoVO=new ContentVideoInfoVO();
        BeanUtils.copyProperties(contentVideoId,contentVideoInfoVO);
        return ResponseResult.ok().data(contentVideoInfoVO);
    }

    @ApiOperation(value = "根据id删除视频内容")
    @DeleteMapping("{id}")
    public ResponseResult deleteContentVideoById(@PathVariable String id){
        return ResponseResult.ok().data(contentVideoService.deleteContentVideoWithById(id));
    }

    /**
     * Test
     */
    @GetMapping("/test-vod/{id}")
    public ResponseResult testVod(@PathVariable String id){
        return vodClient.testVideo(id);
    }

}
