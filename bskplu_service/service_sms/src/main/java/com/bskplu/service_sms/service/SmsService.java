package com.bskplu.service_sms.service;

import java.util.Map;

/**
 * @InterfaceName: SmsService
 * @Description: 邮件发送接口
 * @Author BsKPLu
 * @Date 2022/3/8
 * @Version 1.1
 */
public interface SmsService {
    boolean send (String phone, Map<String, Object> param);
}
