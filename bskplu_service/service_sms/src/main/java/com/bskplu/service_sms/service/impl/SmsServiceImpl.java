package com.bskplu.service_sms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.bskplu.service_base.utils.text.StringUtils;
import com.bskplu.service_sms.service.SmsService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @ClassName: SmsServiceImpl
 * @Description: 邮件服务实现类
 * @Author BsKPLu
 * @Date 2022/3/8
 * @Version 1.1
 */
@Service
public class SmsServiceImpl implements SmsService {

    @Override
    public boolean send(String phone, Map<String, Object> param) {
        return sendPhone(phone, "SMS_1691115xx", param);
    }

    public static boolean sendPhone (String phoneNumbers, String templateCode, Map<String, Object> param) {
        if (StringUtils.isEmpty(phoneNumbers)) {
            return false;
        }
        DefaultProfile profile = DefaultProfile.getProfile(
                "cn-shanghai",
                "你的key",
                "你的密钥");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2020-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("PhoneNumbers", phoneNumbers);
        request.putQueryParameter("SignName", "标签");
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
