package com.bskplu.service_sms.controller;

import com.bskplu.common_utils.utils.ResponseResult;
import com.bskplu.service_base.exception.BusinessException;
import com.bskplu.service_base.utils.text.PhoneUtils;
import com.bskplu.service_base.utils.text.StringUtils;
import com.bskplu.service_sms.service.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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

        //查询redis中是否有跟手机号相对应的验证码 手机号当作key
        String code=redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)){
            //已经发送过了，未过期
            return ResponseResult.ok();
        }
        code=randomCode();
        Map<String,Object> param=new HashMap<>();
        param.put("code",code);
        Boolean isSend=smsService.send(phone,param);

        //发送成功
        if(isSend){
            //将验证码存入redis中，5min过期时间
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return ResponseResult.ok();
        }else {
            return ResponseResult.error().message("发送短信失败");
        }
    }

    /**
     * 生成随机4位验证码
     * @return
     */
    public String randomCode () {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int s = new Random().nextInt(10);
            sb.append(s);
        }
        return sb.toString();
    }

}
