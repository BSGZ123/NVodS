package com.bskplu.service_pay.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bskplu.api_user.RemoteUserClient;
import com.bskplu.api_user.entity.User;
import com.bskplu.api_video.RemoteContentClient;
import com.bskplu.api_video.entity.vo.ContentWebVO;
import com.bskplu.common_utils.constant.OrderConstant;
import com.bskplu.common_utils.utils.ResponseResult;
import com.bskplu.common_utils.utils.UUIDUtils;
import com.bskplu.service_base.exception.TransactionalException;
import com.bskplu.service_pay.entity.PayOrder;
import com.bskplu.service_pay.mapper.PayOrderMapper;
import com.bskplu.service_pay.service.PayOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private final RemoteContentClient remoteContentClient;
    private final RemoteUserClient remoteUserClient;


    /**
     * 创建用户订单模块
     * @param contentId
     * @param userIdByJwtToken
     * @return
     */
    @Override
    @Transactional(rollbackFor = TransactionalException.class)
    public ResponseResult createOrderInfo(String contentId, String userIdByJwtToken) {

        User loginInfo= remoteUserClient.getLoginInfo(userIdByJwtToken);
        ContentWebVO contentWebVO=remoteContentClient.getContentInfoOrder(contentId);

        //创建Order对象 向order对象中设置需要的数据
        PayOrder order=new PayOrder();

        order.setOrderNo(UUIDUtils.getRandomNumber());//生成订单号
        order.setContentId(contentId);//设置课程id
        order.setContentTitle(contentWebVO.getTitle());//设置课程标题
        order.setContentCover(contentWebVO.getCover());//获取课程封面
        order.setAuthorName(contentWebVO.getAuthorName());//设置创作者名字
        order.setTotalFee(contentWebVO.getPrice());//设置作品价格
        order.setUserId(userIdByJwtToken);//订单课程用户id
        order.setMobile(loginInfo.getMobile());//设置用户手机号
        order.setNickname(loginInfo.getNickname());//用户昵称
        order.setStatus(0);//设置订单状态（0，未支付 1，已支付）
        order.setPayType(2);//支付类型 2是支付宝

        baseMapper.insert(order);

        return ResponseResult.ok().data(order.getOrderNo());
    }

    /**
     * 根据订单编号获取订单详情
     * @param orderNo
     * @return
     */
    @Override
    public PayOrder getOrderInfoByOrderNo(String orderNo) {
        return baseMapper.selectOne(new LambdaQueryWrapper<PayOrder>().eq(PayOrder::getOrderNo,orderNo));
    }

    /**
     * 根据用户ID和作品ID查询是否购买作品
     * @param userId
     * @param contentId
     * @return
     */
    @Override
    public boolean getBuyContentStatus(String userId, String contentId) {
        int count=baseMapper.selectCount(new LambdaQueryWrapper<PayOrder>()
                .eq(PayOrder::getUserId,userId)
                .eq(PayOrder::getContentId,contentId)
                .eq(PayOrder::getStatus, OrderConstant.STATUS_1));

        return count>0;
    }

    /**
     * 根据前台获取的用户查询用户所有的用户订单列表
     * @param userIdByJwtToken
     * @return
     */
    @Override
    public ResponseResult getOrdersByUserId(String userIdByJwtToken) {
        return ResponseResult.ok().data(baseMapper.selectList(new LambdaQueryWrapper<PayOrder>().eq(PayOrder::getUserId,userIdByJwtToken)));
    }
}
