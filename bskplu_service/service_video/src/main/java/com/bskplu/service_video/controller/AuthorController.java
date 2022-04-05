package com.bskplu.service_video.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bskplu.api_video.entity.Author;
import com.bskplu.api_video.entity.Content;
import com.bskplu.api_video.entity.vo.AuthorQuery;
import com.bskplu.common_utils.utils.ResponseResult;
import com.bskplu.service_video.service.AuthorService;
import com.bskplu.service_video.service.ContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: AuthorController
 * @Description: 创作者前端控制类
 * @Author BsKPLu
 * @Date 2022/3/16
 * @Version 1.1
 */

@Api(tags = "作者组")
@RestController
@RequestMapping("/service_video/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;
    private final ContentService contentService;

    /**
     * 页面分页处理
     * @param page
     * @param limit
     * @param authorQuery
     * @return
     */
    @ApiOperation(value = "获取作者列表分页数据")
    @PostMapping("/pageList/{page}/{limit}")
    public ResponseResult pageList(@ApiParam(name = "page",value = "当前页",required = true)
                                   @PathVariable Long page,
                                   @ApiParam(name = "limit",value = "每页记录数",required = true)
                                   @PathVariable Long limit,
                                   @ApiParam(name = "authorQuery",value = "查询对象",required = false)
                                               AuthorQuery authorQuery){
        //分页查询
        Page<Author> pageInfo=new Page<>(page,limit);

        //调用业务处理分页条件查询 查询的结果存入到pageInfo
        authorService.pageQuery(pageInfo,authorQuery);

        //获取当前页的数据
        List<Author> records=pageInfo.getRecords();

        //获取总记录数
        long total=pageInfo.getTotal();

        return ResponseResult.ok().dataPage(records,total);
    }


    /**
     * 获得所有作者数据列表
     * @return
     */
    @ApiOperation(value = "作者数据列表")
    @GetMapping("/getAllAuthorList")
    public ResponseResult getAllAuthorList(){
        return ResponseResult.ok().data(authorService.list(null));
    }

    /**
     * 根据作者id查询信息
     * @param id
     * @return
     */
    @ApiOperation("根据作者id查询详细信息")
    @GetMapping("/getAuthorWithId/{id}")
    public ResponseResult getAuthorWithId(@ApiParam(name = "作者id",required = true) @PathVariable String id){

        //查询
        Author author=authorService.getById(id);

        //根据作者id查询该作者所有的作品列表
        List<Content> contentList=contentService.selectByAuthorId(id);

        return ResponseResult.ok().data("author",author).data("contentList",contentList);

    }


    /**
     * 添加作者
     * @param author
     * @return
     */
    @ApiOperation(value = "添加作者")
    @PostMapping("/addAuthor")
    public ResponseResult addAuthor(@ApiParam(name = "author",value = "作者对象",required = true)
                                    @RequestBody Author author){
        return ResponseResult.toOk(authorService.save(author));
    }


    /**
     * 更新作者信息
     * @param author
     * @return
     */
    @ApiOperation(value = "更新创作者")
    @PostMapping("/updateAuthor")
    public ResponseResult updateAuthor(@ApiParam(name = "author",value = "作者对象",required = true)
                                                   @RequestBody Author author){
        return ResponseResult.toOk(authorService.updateById(author));
    }


    /**
     * 根据作者id删除作者信息
     * @param id
     * @return
     */
    @ApiOperation("根据作者id删除作者信息")
    @PostMapping("/deleteAuthorById/{id}")
    public ResponseResult deleteById(@ApiParam(name = "作者id") @PathVariable Long id){
        return ResponseResult.toOk(authorService.removeById(id));
    }



}
