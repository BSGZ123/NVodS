package com.bskplu.service_vod.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;

/**
 * @ClassName: AliyunVodSDKUtils
 * @Description: 阿里云点播工具类
 * @Author BsKPLu
 * @Date 2022/3/6
 * @Version 1.1
 */
public class AliyunVodSDKUtils {
    /**
     * 初始化视频点播客户
     *
     * @param accessKeyId     访问密钥id
     * @param accessKeySecret 访问密钥的秘密
     * @return {@link DefaultAcsClient}
     * @throws ClientException 客户端异常
     */
    public static DefaultAcsClient initVodClient (String accessKeyId, String accessKeySecret) throws ClientException {
        // 点播服务接入区域 不能修改
        String regionId = "cn-shanghai";
        DefaultProfile profile = DefaultProfile.getProfile(
                regionId,
                accessKeyId,
                accessKeySecret);
        return new DefaultAcsClient(profile);
    }

    /**
     * 获取视频凭证
     * @param videoId
     * @return GetVideoPlayAuthResponse
     */
    public static String init(String videoId){
        try {
            final DefaultAcsClient defaultAcsClient = initVodClient(VodConstant.ACCESS_KEY_ID, VodConstant.ACCESS_KEY_SECRET);
            //创建获取凭证request和response对象
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            //向request设置视频id
            request.setVideoId(videoId);
            // 调用方法得到凭证
            GetVideoPlayAuthResponse response = defaultAcsClient.getAcsResponse(request);
            // 获取凭证
            return response.getPlayAuth();
        } catch (ClientException e) {
            e.printStackTrace();
            return "";
        }
    }
}
