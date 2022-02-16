package com.bskplu.api_user;

import com.bskplu.api_user.entity.User;
import com.bskplu.api_user.factory.RemoteUserFactory;
import com.bskplu.common_utils.constant.CloudConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @InterfaceName: RemoteUserClient
 * @Description: 用户API接口
 * @Author BsKPLu
 * @Date 2022/2/12
 * @Version 1.1
 */
@FeignClient(value = CloudConstant.SERVICE_USER,fallbackFactory = RemoteUserFactory.class)
public interface RemoteUserClient {

    /**
     * 根据用户id获取用户信息
     * @param id
     * @return
     */
    @GetMapping("/service_user/user/getUserInfoOrderById/{id}")
    public User getLoginInfo(@PathVariable String id);

}
