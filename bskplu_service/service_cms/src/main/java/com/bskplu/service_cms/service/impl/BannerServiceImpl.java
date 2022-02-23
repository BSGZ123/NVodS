package com.bskplu.service_cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bskplu.service_cms.entity.Banner;
import com.bskplu.service_cms.mapper.BannerMapper;
import com.bskplu.service_cms.service.BannerService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: BannerServiceImpl
 * @Description: 首页banner 服务实现类
 * @Author BsKPLu
 * @Date 2022/2/24
 * @Version 1.1
 */
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper,Banner> implements BannerService {
    @Override
    public List<Banner> selectAllBanner() {
        return null;
    }

    @Override
    public boolean updateBanner(Banner banner) {
        return false;
    }

    @Override
    public boolean addBanner(Banner crmBanner) {
        return false;
    }

    @Override
    public void deleteBanner(String id) {

    }
}
