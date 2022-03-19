package com.bskplu.service_pay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bskplu.service_pay.entity.PayLog;

import java.util.Map;

/**
 * @InterfaceName: PayLogService
 * @Description: 支付日志服务接口
 * @Author BsKPLu
 * @Date 2022/3/19
 * @Version 1.1
 */
public interface PayLogService extends IService<PayLog> {
    String aliPay(String orderId);

    boolean updateOrdersStatus(Map<String,String> map);
}
