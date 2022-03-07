package com.bskplu.service_upload.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @InterfaceName: UploadService
 * @Description: 上传服务接口
 * @Author BsKPLu
 * @Date 2022/3/7
 * @Version 1.1
 */
public interface UploadService {
    String uploadFile(MultipartFile file);
}
