package com.bskplu.service_cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bskplu.service_cms.entity.Banner;

import java.util.List;

/**
 * @InterfaceName: BannerService
 * @Description: 首页banner 服务类
 * @Author BsKPLu
 * @Date 2022/2/23
 * @Version 1.1
 */

public interface BannerService extends IService<Banner> {
    /**
     * 缓存查询所有焦点图
     * @return
     */
    List<Banner> selectAllBanner ();

    /**
     * 编辑焦点图
     */
    boolean updateBanner(Banner banner);

    /**
     * 新增焦点图
     */
    boolean addBanner(Banner crmBanner);

    /**
     * 删除焦点图
     */
    void deleteBanner(String id);
}
