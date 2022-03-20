package com.bskplu.service_pay.controller;

import com.bskplu.common_utils.utils.ResponseResult;
import com.bskplu.service_pay.service.PayLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: PayLogController
 * @Description: 支付日志 前端控制类
 * @Author BsKPLu
 * @Date 2022/3/20
 * @Version 1.1
 */
@Api(tags = "支付log")
@RestController
@RequestMapping("/service_pay/pay-log")
@RequiredArgsConstructor
public class PayLogController {
    private final PayLogService payService;

    @ApiOperation("支付宝电脑支付页面")
    @GetMapping("/aliPay/{orderNo}")
    public ResponseResult aliPay(@PathVariable("orderNo") String orderNo) {
        String form = payService.aliPay(orderNo);
        return ResponseResult.ok().data("form", form);
    }
}
