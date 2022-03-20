package com.bskplu.service_pay.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayTradePageMergePayModel;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bskplu.common_utils.utils.ServletUtils;
import com.bskplu.service_base.exception.BusinessException;
import com.bskplu.service_pay.common.config.AliPayApiConfig;
import com.bskplu.service_pay.common.properties.AliPayProperties;
import com.bskplu.service_pay.entity.PayLog;
import com.bskplu.service_pay.entity.PayOrder;
import com.bskplu.service_pay.mapper.PayLogMapper;
import com.bskplu.service_pay.service.PayLogService;
import com.bskplu.service_pay.service.PayOrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @ClassName: PayLogServiceImpl
 * @Description: 支付日志实现类
 * @Author BsKPLu
 * @Date 2022/3/20
 * @Version 1.1
 */
@Service
@RequiredArgsConstructor
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {

    private Logger logger= LoggerFactory.getLogger(getClass());

    //字符编码
    private static final String CHARSET = "UTF-8";

    private static final String EXPIRE = "30m";

    private final AliPayProperties aliPayProperties;
    private final AliPayApiConfig aliPayApiConfig;
    private final PayOrderService payOrderService;


    @Override
    public String aliPay(String orderId) {
        //根据订单号查询信息
        QueryWrapper<PayOrder> wrapper =new QueryWrapper<>();
        wrapper.eq("order_no",orderId);
        PayOrder order= payOrderService.getOne(wrapper);

        //支付内容参数
        AlipayTradePagePayModel model= new AlipayTradePagePayModel();
        model.setOutTradeNo(order.getOrderNo());

        model.setProductCode("FAST_INSTANT_TRADE_PAY");
        //设定商品金额
        model.setTotalAmount(order.getTotalFee().toString());
        model.setSubject(order.getContentTitle());
        model.setBody("支付宝支付，共"+order.getTotalFee()+"元");

        //设置商品最晚付款完成时间
        model.setTimeoutExpress(EXPIRE);

        //构建支付请求
        AlipayTradePagePayRequest request=new AlipayTradePagePayRequest();
        //同步回调
        request.setReturnUrl(aliPayProperties.getReturnUrl());

        //异步回调
        request.setNotifyUrl(aliPayProperties.getNotifyUrl());
        request.setBizModel(model);

        //请求发送
        try {
            String form=aliPayApiConfig.getAliPayClient().pageExecute(request).getBody();
            HttpServletResponse response= ServletUtils.getResponse();
            response.setContentType("text/html;charset="+CHARSET);
            return form;
        } catch (AlipayApiException e) {
            e.printStackTrace();
            throw new BusinessException("支付宝网关支付失败！");
        }
    }

    @Override
    public boolean updateOrdersStatus(Map<String, String> map) {
        return this.payOrderService.update(new PayOrder().setStatus(1),new LambdaQueryWrapper<PayOrder>()
                .eq(PayOrder::getOrderNo,String.valueOf(map.get("out_trade_no"))));
    }
}
