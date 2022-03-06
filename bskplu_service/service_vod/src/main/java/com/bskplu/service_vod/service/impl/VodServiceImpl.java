package com.bskplu.service_vod.service.impl;

import com.bskplu.service_vod.service.VodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName: VodServiceImpl
 * @Description: 视频点播服务实现类
 * @Author BsKPLu
 * @Date 2022/3/6
 * @Version 1.1
 */
@Service
@Slf4j
public class VodServiceImpl implements VodService {
    @Override
    public String uploadVideo(MultipartFile file) {
        return null;
    }

    @Override
    public void deleteVideo(String videoId) {

    }
}
