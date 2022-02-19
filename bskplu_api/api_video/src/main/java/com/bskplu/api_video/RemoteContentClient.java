package com.bskplu.api_video;

import com.bskplu.common_utils.constant.CloudConstant;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @InterfaceName: RemoteContentClient
 * @Description: video api接口
 * @Author BsKPLu
 * @Date 2022/2/19
 * @Version 1.1
 */
@FeignClient(value = CloudConstant.SERVICE_VIDEO)
public interface RemoteContentClient {
}
