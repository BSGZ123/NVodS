package com.bskplu.api_vod;

import com.bskplu.api_vod.factory.VodCallBackFactory;
import com.bskplu.common_utils.constant.CloudConstant;
import com.bskplu.common_utils.utils.ResponseResult;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @InterfaceName: VodClient
 * @Description: 视频点播接口
 * @Author BsKPLu
 * @Date 2022/2/26
 * @Version 1.1
 */
@FeignClient(name = CloudConstant.SERVICE_VOD,fallbackFactory = VodCallBackFactory.class)
public interface VodClient {

    /**
     * 视频id
     * @param videoId
     * @return
     */
    @PostMapping("/service_vod/vod/delete-vod/{videoId}")
    public ResponseResult removeVideo (@PathVariable @Param("videoId") String videoId);

    /**
     * 测试远程调用是否连通接口
     * @param videoId
     * @return
     */
    @GetMapping("/service_vod/vod/test-vod/{videoId}")
    public ResponseResult testVideo (@PathVariable @Param("videoId") String videoId);
}
