package com.bskplu.service_cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bskplu.service_cms.entity.Banner;
import com.bskplu.service_cms.mapper.BannerMapper;
import com.bskplu.service_cms.service.BannerService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
    /**
     * 缓存查询所有焦点图
     * @return
     */
    @Cacheable(value = "banner",key = "'selectIndexList'")
    @Override
    public List<Banner> selectAllBanner() {
        QueryWrapper<Banner> wrapper=new QueryWrapper<>();
        //根据id进行降序排列，显示排列后的前3条记录
        wrapper.orderByDesc("id");
        wrapper.last("limit 3");
        return baseMapper.selectList(null);
    }

    @Override
    @CacheEvict(value = "banner",allEntries = true)
    public boolean updateBanner(Banner banner) {
        return this.updateById(banner);
    }

    @Override
    @CacheEvict(value = "banner",allEntries = true)
    public boolean addBanner(Banner crmBanner) {
        return this.save(crmBanner);
    }

    @Override
    @CacheEvict(value = "banner",allEntries = true)
    public void deleteBanner(String id) {
        this.removeById(id);
    }
}
