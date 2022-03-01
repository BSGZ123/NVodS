package com.bskplu.api_pay;

import com.bskplu.api_pay.factory.RemotePayOrderFactory;
import com.bskplu.common_utils.constant.CloudConstant;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @InterfaceName: RemotePayOrderClient
 * @Description: 支付api接口
 * @Author BsKPLu
 * @Date 2022/3/1
 * @Version 1.1
 */
@FeignClient(value = CloudConstant.SERVICE_PAY, fallbackFactory = RemotePayOrderFactory.class)
public interface RemotePayOrderClient {
    /**
     * 得到购买内容 并根据用户id和内容id判断用户是否购买商品
     * @param userId
     * @param contentId
     * @return
     */
    @GetMapping("/service_pay/pay-order/getBuyContent/{userId}/{contentId}")
    public boolean getBuyContent(@PathVariable @Param("userId") String userId, @PathVariable @Param("contentId") String contentId);
}
