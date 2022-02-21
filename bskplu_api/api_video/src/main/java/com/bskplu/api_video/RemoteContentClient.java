package com.bskplu.api_video;

import com.bskplu.api_video.entity.vo.ContentWebVO;
import com.bskplu.api_video.factory.RemoteContentClientFactory;
import com.bskplu.common_utils.constant.CloudConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @InterfaceName: RemoteContentClient
 * @Description: video api接口
 * @Author BsKPLu
 * @Date 2022/2/19
 * @Version 1.1
 */
@FeignClient(value = CloudConstant.SERVICE_VIDEO,fallbackFactory = RemoteContentClientFactory.class)
/**
 * 根据id查询作品信息
 */
public interface RemoteContentClient {
    @GetMapping("/service_video/content/getContentInfoOrder/{id}")
    public ContentWebVO getContentInfoOrder(@PathVariable("id") String id);
}
