package com.bskplu.service_vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.bskplu.common_utils.utils.ResultCode;
import com.bskplu.service_base.exception.BusinessException;
import com.bskplu.service_base.utils.text.StringUtils;
import com.bskplu.service_vod.service.VodService;
import com.bskplu.service_vod.utils.AliyunVodSDKUtils;
import com.bskplu.service_vod.utils.VodConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

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
        try {
            //获取文件输入流
            InputStream inputStream=file.getInputStream();
            //获取文件名
            String originalFilename=file.getOriginalFilename();

            //断言抛出错误异常，截取标题
            assert originalFilename!=null:"断言：这里获取的文件名不能为空";
            String title=originalFilename.substring(0,originalFilename.lastIndexOf("."));

            //上传请求
            UploadStreamRequest request=new UploadStreamRequest(VodConstant.ACCESS_KEY_ID,
                    VodConstant.ACCESS_KEY_SECRET,
                    title,originalFilename,inputStream);
            UploadVideoImpl uploadVideo=new UploadVideoImpl();

            //上传完毕，获取服务器返回的信息
            UploadStreamResponse response=uploadVideo.uploadStream(request);

            //设置回调URL无效，不影响上传，会获取到videoId也会返回错误码
            //其他错误，会导致获取不到videoId,这个就要根据具体错误码判断
            String videoId=response.getVideoId();
            if(!response.isSuccess()){
                String errorMessage="阿里云上传错误：" + "code：" + response.getCode() + ", message：" + response.getMessage();
                log.error(errorMessage);
                if(StringUtils.isEmpty(videoId)){
                    throw new BusinessException(ResultCode.ERROR,errorMessage);
                }
            }
            return videoId;


        } catch (IOException e) {
            throw new BusinessException(ResultCode.ERROR,"Vod服务上传失败！");
        }
    }

    @Override
    public void deleteVideo(String videoId) {

        try {
            //获取客户请求
            DefaultAcsClient client= AliyunVodSDKUtils.initVodClient(VodConstant.ACCESS_KEY_ID,VodConstant.ACCESS_KEY_SECRET);
            DeleteVideoRequest request=new DeleteVideoRequest();
            //设定需要删除的视频id
            request.setVideoIds(videoId);
            DeleteVideoResponse response=client.getAcsResponse(request);
            log.info("删除视频资源成功:{}", response);

        } catch (ClientException e) {
            throw new BusinessException(ResultCode.ERROR,"视频删除失败！");
        }


    }
}
