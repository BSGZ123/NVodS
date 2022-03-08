package com.bskplu.service_sms.controller;

import com.bskplu.common_utils.utils.ResponseResult;
import com.bskplu.service_base.exception.BusinessException;
import com.bskplu.service_base.utils.text.PhoneUtils;
import com.bskplu.service_sms.service.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: SmsController
 * @Description: 邮件短信服务前端控制类
 * @Author BsKPLu
 * @Date 2022/3/8
 * @Version 1.1
 */
@RestController
@RequestMapping("/service_sms/api/sms")
@RequiredArgsConstructor
public class SmsController {
    private final SmsService smsService;

    private final RedisTemplate<String,String> redisTemplate;

    public ResponseResult send(String phone){
        if(!PhoneUtils.isPhone(phone)){
            throw new BusinessException("该手机号不符合规范！");
        }
        return null;
    }

}
