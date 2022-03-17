package com.bskplu.service_video.controller;

import com.bskplu.api_video.entity.Chapter;
import com.bskplu.common_utils.utils.ResponseResult;
import com.bskplu.service_video.service.ChapterService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "新增章节")
    @PostMapping
    public ResponseResult insertChapter(@RequestBody Chapter chapter){
        return ResponseResult.toOk(chapterService.save(chapter));
    }

    @ApiOperation(value = "修改章节")
    @PutMapping
    public ResponseResult updateChapter(@RequestBody Chapter chapter){
        return ResponseResult.toOk(chapterService.updateById(chapter));
    }

    @ApiOperation(value = "删除章节")
    @DeleteMapping("{id}")
    public ResponseResult deleteChapterById(@ApiParam(name = "id",value = "章节id",required = true) @PathVariable String id){
        return ResponseResult.toOk(chapterService.deleteChapterById(id));
    }

    @ApiOperation(value = "章节或小节排序最大值")
    @PostMapping("/getContentOrVideoSortMax/{type}")
    public ResponseResult getContentOrVideoSortMax(@PathVariable Integer type){
        return ResponseResult.ok().data(this.chapterService.getContentOrVideoSortMax(type));
    }

}
