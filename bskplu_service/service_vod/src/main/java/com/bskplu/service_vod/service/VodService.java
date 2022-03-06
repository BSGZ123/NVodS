package com.bskplu.service_vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @InterfaceName: VodService
 * @Description: 点播服务接口
 * @Author BsKPLu
 * @Date 2022/3/6
 * @Version 1.1
 */
public interface VodService {
    String uploadVideo(MultipartFile file);
    void deleteVideo(String videoId);
}
