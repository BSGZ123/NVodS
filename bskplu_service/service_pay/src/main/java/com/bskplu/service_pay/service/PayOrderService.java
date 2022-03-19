package com.bskplu.service_pay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bskplu.common_utils.utils.ResponseResult;
import com.bskplu.service_pay.entity.PayOrder;

/**
 * @InterfaceName: PayOrderService
 * @Description: 支付订单服务接口
 * @Author BsKPLu
 * @Date 2022/3/19
 * @Version 1.1
 */
public interface PayOrderService extends IService<PayOrder> {

    //创建用户订单信息
    ResponseResult createOrderInfo(String contentId,String userIdByJwtToken);

    //根据订单编号获取订单信息
    PayOrder getOrderInfoByOrderNo(String orderNo);

    //根据用户id和内容id查询用户是否购买了该内容
    boolean getBuyContentStatus(String userId,String contentId);

    //根据用户id获取用户所有的订单
    ResponseResult getOrdersByUserId(String userIdByJwtToken);
}
