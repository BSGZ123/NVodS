package com.bskplu.service_video.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bskplu.api_video.entity.ContentVideo;
import com.bskplu.api_video.entity.vo.ContentVideoInfoVO;
import com.bskplu.api_video.entity.vo.ContentVideoVO;
import com.bskplu.service_video.mapper.ContentVideoMapper;
import com.bskplu.service_video.service.ContentVideoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName: ContentVideoServiceImpl
 * @Description: 作品小结视频服务实现类
 * @Author BsKPLu
 * @Date 2022/3/10
 * @Version 1.1
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ContentVideoServiceImpl extends ServiceImpl<ContentVideoMapper, ContentVideo> implements ContentVideoService {
    @Override
    public boolean getCountByChapterId(String id) {
        return false;
    }

    @Override
    public boolean addContentVideo(ContentVideoVO contentVideoVO) {
        return false;
    }

    @Override
    public boolean updateContentVideo(ContentVideoInfoVO contentVideoInfoVO) {
        return false;
    }

    @Override
    public boolean deleteContentVideoWithById(String id) {
        return false;
    }

    @Override
    public boolean deleteContentVideoWithByContentId(String id) {
        return false;
    }
}
