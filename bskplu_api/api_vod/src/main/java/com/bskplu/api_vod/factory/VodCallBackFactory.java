package com.bskplu.api_vod.factory;

import com.bskplu.api_vod.VodClient;
import com.bskplu.common_utils.utils.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @ClassName: VodCallBackFactory
 * @Description: vod回调工厂类
 * @Author BsKPLu
 * @Date 2022/2/26
 * @Version 1.1
 */
@Component
public class VodCallBackFactory implements FallbackFactory<VodClient> {
    private static final Logger logger= LoggerFactory.getLogger(VodCallBackFactory.class);
    @Override
    public VodClient create(Throwable cause) {
        logger.error("终端检测接口服务调用失败:{}", cause.getMessage());
        return new VodClient() {
            @Override
            public ResponseResult removeVideo(String videoId) {
                return ResponseResult.error().data("远程视频删除失败!");
            }

            @Override
            public ResponseResult testVideo(String videoId) {
                return ResponseResult.error().data("远程视频删除失败!");
            }
        };
    }
}
