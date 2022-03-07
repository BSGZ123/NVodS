package com.bskplu.service_upload.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.bskplu.service_upload.service.UploadService;
import com.bskplu.service_upload.utils.OssConstant;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * @ClassName: UploadServiceImpl
 * @Description: 上传服务实现类
 * @Author BsKPLu
 * @Date 2022/3/7
 * @Version 1.1
 */
@Component
@Slf4j
public class UploadServiceImpl implements UploadService {
    @Override
    public String uploadFile(MultipartFile file) {

        String endpoint= OssConstant.ENDPOINT;
        // aliYun云账号AccessKey有所有API访问权限，注意安全
        String accessKeyId = OssConstant.ASSESS_KEY_ID;
        String accessKeySecret = OssConstant.ASSESS_KEY_SECRET;
        String bucketName = OssConstant.BUCKET_NAME;
        // 创建OSSClient实例
        OSS ossClient = null;
        try {
            // 创建OSSClient实例
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            // 上传文件流。
            InputStream inputStream = file.getInputStream();
            //处理文件名称
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String fileName = uuid + file.getOriginalFilename();

            /*把同一天上传的文件 放入到同一个以时间命名的文件夹当中  2022/3/7/fileName*/
            String date = new DateTime().toString("yyyy/MM/dd");
            fileName = date + "/" + fileName;

            ossClient.putObject(bucketName, fileName, inputStream);

            log.info("文件上次完毕:{}", OssConstant.PATH_NAME + fileName);
            return OssConstant.PATH_NAME + fileName;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭OSSClient
            assert ossClient != null;
            ossClient.shutdown();
        }
        return null;
    }
}
