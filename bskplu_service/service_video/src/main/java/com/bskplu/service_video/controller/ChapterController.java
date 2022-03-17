package com.bskplu.service_video.controller;

import com.bskplu.common_utils.utils.ResponseResult;
import com.bskplu.service_video.service.ChapterService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: ChapterController
 * @Description: 小节控制类
 * @Author BsKPLu
 * @Date 2022/3/16
 * @Version 1.1
 */
@RestController
@RequestMapping("/service_video/chapter")
@RequiredArgsConstructor
public class ChapterController {
    private final ChapterService chapterService;

    @ApiOperation(value = "获取章节列表")
    @GetMapping("getChapterContentVideo/{contentId}")
    public ResponseResult getChapterContentVideo(@PathVariable String contentId){
        return ResponseResult.ok().data(chapterService.getChapterContentVideo(contentId));
    }


}
