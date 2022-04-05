package com.bskplu.service_cms.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bskplu.common_utils.utils.ResponseResult;
import com.bskplu.service_cms.entity.Banner;
import com.bskplu.service_cms.service.BannerService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: BannerController
 * @Description: 首页banner 前端控制器
 * @Author BsKPLu
 * @Date 2022/2/23
 * @Version 1.1
 */
@RestController
@RequestMapping("/service_cms/banner")
@RequiredArgsConstructor
public class BannerController {
    private final BannerService bannerService;

    /**
     * 查询所有banner
     * @return
     */
    @GetMapping("/getAllBanner")
    public ResponseResult getAllBanner(){
        return ResponseResult.ok().data("list",bannerService.selectAllBanner());
    }

    /**
     * 分页查询banner
     * @param page
     * @param limit
     * @param banner
     * @return
     */
    @RequestMapping("pageBanner/{page}/{limit}")
    public ResponseResult pageBanner(@PathVariable("page") long page,@PathVariable("limit") long limit,@RequestBody Banner banner){
        Page<Banner> pageBanner =new Page<>(page,limit);
        bannerService.page(pageBanner,new LambdaQueryWrapper<Banner>().like(Banner::getTitle,banner.getTitle()));
        return ResponseResult.ok().data("items",pageBanner.getRecords()).data("total",pageBanner.getTotal());
    }

    /**
     * crm的横幅
     * @param crmBanner
     * @return
     */
    @PostMapping("addBanner")
    public ResponseResult addBanner(@RequestBody Banner crmBanner){
        Boolean b=bannerService.addBanner(crmBanner);
        bannerService.selectAllBanner();
        return ResponseResult.toOk(b);
    }

    @ApiOperation(value = "获取banner")
    @GetMapping("get/{id}")
    public ResponseResult get(@PathVariable String id){
        Banner banner=bannerService.getById(id);
        return ResponseResult.ok().data("item",banner);
    }

    @ApiOperation(value = "修改Banner")
    @PutMapping("update")
    public ResponseResult updateById(@RequestBody Banner banner){
        Boolean b=bannerService.updateById(banner);
        bannerService.selectAllBanner();
        return ResponseResult.toOk(b);
    }

    @ApiOperation(value = "删除Banner")
    @DeleteMapping("remove/{id}")
    public ResponseResult remove(@PathVariable String id){
        bannerService.deleteBanner(id);
        bannerService.selectAllBanner();
        return ResponseResult.ok();
    }


}
