package com.bskplu.service_pay.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bskplu.common_utils.utils.ResponseResult;
import com.bskplu.service_pay.entity.PayOrder;
import com.bskplu.service_pay.mapper.PayOrderMapper;
import com.bskplu.service_pay.service.PayOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @ClassName: PayOrderServiceImpl
 * @Description: 商品订单服务实现类
 * @Author BsKPLu
 * @Date 2022/3/20
 * @Version 1.1
 */
@Service
@RequiredArgsConstructor
public class PayOrderServiceImpl extends ServiceImpl<PayOrderMapper, PayOrder> implements PayOrderService {
    @Override
    public ResponseResult createOrderInfo(String contentId, String userIdByJwtToken) {
        return null;
    }

    @Override
    public PayOrder getOrderInfoByOrderNo(String orderNo) {
        return null;
    }

    @Override
    public boolean getBuyContentStatus(String userId, String contentId) {
        return false;
    }

    @Override
    public ResponseResult getOrdersByUserId(String userIdByJwtToken) {
        return null;
    }
}
