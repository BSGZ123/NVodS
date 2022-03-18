package com.bskplu.service_video.controller;

import com.bskplu.api_pay.RemotePayOrderClient;
import com.bskplu.api_video.entity.vo.ChapterVO;
import com.bskplu.api_video.entity.vo.ContentVo;
import com.bskplu.api_video.entity.vo.ContentWebVO;
import com.bskplu.common_utils.utils.JwtUtils;
import com.bskplu.common_utils.utils.ResponseResult;
import com.bskplu.service_base.utils.text.StringUtils;
import com.bskplu.service_video.service.ChapterService;
import com.bskplu.service_video.service.ContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @ApiOperation(value = "修改作品内容")
    @PostMapping("/updateContentInfo")
    public ResponseResult updateContentInfo(ContentVo contentVo){
        return ResponseResult.toOk(contentService.updateContentInfo(contentVo));
    }

    @ApiOperation(value = "作品预览")
    @PostMapping("/getContentPreview/{id}")
    public ResponseResult getContentPreview(@PathVariable String id){
        return this.contentService.getContentPreview(id);
    }

    @ApiOperation(value = "作品发布")
    @PostMapping("/sendContent/{id}")
    public ResponseResult sendContent(@PathVariable String id){
        return this.contentService.sendContent(id);
    }

    @ApiOperation(value = "作品列表分页")
    @GetMapping("/getContentListPage")
    public ResponseResult getContentListPage(ContentVo contentVo){
        return this.contentService.getContentListPage(contentVo);
    }

    @ApiOperation(value = "删除作品列表")
    @PostMapping("/deleteContentById/{id}")
    public ResponseResult deleteContentById(@PathVariable String id){
        return this.contentService.deleteContentById(id);
    }

    @ApiOperation(value = "根据作品ID及获取登录用户ID判断该作品用户是否购买")
    @GetMapping("/getContentDetailByContentId/{contentId}")
    public ResponseResult getContentDetail(@PathVariable String contentId, HttpServletRequest request){
        //查询作品内容及作者信息
        ContentWebVO contentWebVO=contentService.selectContentDetailById(contentId);

        //查询当前作品的章节信息
        List<ChapterVO> chapterVOList=chapterService.getChapterContentVideo(contentId);

        //判断当前作品是否已经支付
        String uid= JwtUtils.getUserIdByJwtToken(request);
        Boolean isBuy=false;

        if(!StringUtils.isEmpty(uid)){
            //查询是否已经付费
            isBuy=remotePayOrderClient.getBuyContent(uid,contentId);

        }
        return ResponseResult.ok()
                .data("content",contentWebVO)
                .data("chapterVoList",chapterVOList)
                .data("isBuyContent",isBuy);
    }

    @ApiOperation(value = "根据作品id查询作品信息")
    @GetMapping("/getContentInfoOrder/{id}")
    public ContentWebVO getContentInfoOrder(@PathVariable("id") String id){
        return contentService.selectContentDetailById(id);
    }


}
