package com.bskplu.service_vod.controller;

import com.bskplu.common_utils.utils.ResponseResult;
import com.bskplu.service_base.exception.BusinessException;
import com.bskplu.service_vod.service.VodService;
import com.bskplu.service_vod.utils.AliyunVodSDKUtils;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName: VodController
 * @Description: 视频点播前端控制类
 * @Author BsKPLu
 * @Date 2022/3/6
 * @Version 1.1
 */
@RestController
@Slf4j
@RequestMapping("/service_vod/vod")
@RequiredArgsConstructor
public class VodController {
    private final VodService vodService;

    @PostMapping("upload")
    public ResponseResult uploadVideo(@RequestParam("file") MultipartFile file){
        String videoId=vodService.uploadVideo(file);
        return ResponseResult.ok().message("视频上传成功").data("videoId",videoId);
    }

    @PostMapping("/delete-vod/{videoId}")
    public ResponseResult removeVideo(@PathVariable String videoId){
        vodService.deleteVideo(videoId);
        return ResponseResult.ok().message("视频删除成功！");
    }

    @GetMapping("/test-vod/{id}")
    public ResponseResult testVod(@PathVariable String id){
        return ResponseResult.ok().data("请求成功"+id);
    }

    @ApiOperation(value = "根据视频id获取视频播放凭证")
    @GetMapping("getPlayAuth/{id}")
    public ResponseResult getPlayAuth(@PathVariable String id){
        try {
            String playAuth = AliyunVodSDKUtils.init(id);
            return ResponseResult.ok().data("playAuth", playAuth);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("获取凭证失败");
        }
    }


}
