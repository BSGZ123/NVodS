package com.bskplu.service_video.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bskplu.api_video.entity.ContentDescription;
import com.bskplu.service_video.mapper.ContentDescriptionMapper;
import com.bskplu.service_video.service.ContentDescriptionService;
import org.springframework.stereotype.Service;

/**
 * @ClassName: ContentDescriptionServiceImpl
 * @Description: 作品简介服务实现类
 * @Author BsKPLu
 * @Date 2022/3/10
 * @Version 1.1
 */
@Service
public class ContentDescriptionServiceImpl extends ServiceImpl<ContentDescriptionMapper, ContentDescription> implements ContentDescriptionService {
}
