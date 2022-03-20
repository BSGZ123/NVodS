package com.bskplu.service_pay.controller;

import com.bskplu.api_pay.RemotePayOrderClient;
import com.bskplu.common_utils.utils.JwtUtils;
import com.bskplu.common_utils.utils.ResponseResult;
import com.bskplu.service_pay.service.PayOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: PayOrderController
 * @Description: 支付订单 前端控制类
 * @Author BsKPLu
 * @Date 2022/3/20
 * @Version 1.1
 */
@Api(tags = "支付管理")
@RestController
@RequestMapping("/service_pay/pay-order")
@RequiredArgsConstructor
public class PayOrderController implements RemotePayOrderClient {
    private final PayOrderService payOrderService;

    @ApiOperation(value = "创建用户订单")
    @PostMapping("createOrderInfo/{contentId}")
    public ResponseResult createOrderInfo(@PathVariable String contentId, HttpServletRequest request) {
        try {
            return payOrderService.createOrderInfo(contentId, JwtUtils.getUserIdByJwtToken(request));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.error("创建用户订单失败");
        }
    }

    @ApiOperation(value = "根据订单编号查询订单信息")
    @GetMapping("getOrderInfoByOrderNo/{orderNo}")
    public ResponseResult getOrderInfoByOrderNo(@PathVariable String orderNo) {
        return ResponseResult.ok().data(payOrderService.getOrderInfoByOrderNo(orderNo));
    }

    @ApiOperation(value = "根据用户ID获取拥有的订单信息")
    @GetMapping("getOrdersByUserId")
    public ResponseResult getOrdersByUserId (HttpServletRequest request) {
        return payOrderService.getOrdersByUserId(JwtUtils.getUserIdByJwtToken(request));
    }

    @Override
    @ApiOperation(value = "根据用户ID和作品ID查询是否购买改作品")
    @GetMapping("getBuyContent/{userId}/{contentId}")
    public boolean getBuyContent(@PathVariable String userId, @PathVariable String contentId) {
        return payOrderService.getBuyContentStatus(userId, contentId);
    }
}
