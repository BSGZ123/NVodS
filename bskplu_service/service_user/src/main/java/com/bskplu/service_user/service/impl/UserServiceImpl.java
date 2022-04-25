package com.bskplu.service_user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bskplu.api_user.entity.User;
import com.bskplu.common_utils.utils.JwtUtils;
import com.bskplu.common_utils.utils.MD5;
import com.bskplu.service_base.controller.core.vo.LoginVo;
import com.bskplu.service_base.controller.core.vo.RegisterVo;
import com.bskplu.service_base.exception.BusinessException;
import com.bskplu.service_base.utils.text.StringUtils;
import com.bskplu.service_user.mapper.UserMapper;
import com.bskplu.service_user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @ClassName: UserServiceImpl
 * @Description: 用户服务业务实现类
 * @Author BsKPLu
 * @Date 2022/2/17
 * @Version 1.1
 */
@Service
@RequiredArgsConstructor//生成带有必须函数的构造函数
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final RedisTemplate<String,String> redisTemplate;
    @Override
    public String login(LoginVo loginVo) {
        String phone =loginVo.getPhone();
        String password =loginVo.getPassword();
        if(StringUtils.isEmpty(phone) || StringUtils.isEmpty(password)){//传入参数是否为空
            throw new BusinessException("缺少参数");
        }
        User user =baseMapper.selectOne(new QueryWrapper<User>().eq("mobile",phone));//查询并获取用户信息
        if(user ==null){
            throw new BusinessException("用户不存在");
        }
        if(!MD5.encrypt(password).equals(user.getPassword())){
            throw new BusinessException("输入密码错误");
        }
        if(Boolean.TRUE.equals(user.getIsDisabled())){//确认用户状态
            throw new BusinessException("用户已被禁用");
        }
        return JwtUtils.getJwtToken(user.getId(),user.getNickname());//验证完成后返回token
    }

    @Override
    public void register(RegisterVo registerVo) {
        //获取注册信息
        String nickname=registerVo.getNickname();
        String phone=registerVo.getPhone();
        String password=registerVo.getPassword();
        String code=registerVo.getCode();

        if(StringUtils.isEmpty(phone) || StringUtils.isEmpty(password) || StringUtils.isEmpty(code)){
            throw new BusinessException("注册参数不完整");
        }
        //校验验证码 从redis获取
        String phonecode=redisTemplate.opsForValue().get(phone);
        if(!code.equals(phonecode)){
            throw new BusinessException("验证码不正确");
        }
        //查询数据库中是否已有该手机号
        Integer count = baseMapper.selectCount(new LambdaQueryWrapper<User>().eq(User::getMobile, phone));
        if(count>0){
            throw new BusinessException("该手机号已被注册");
        }
        User member=new User();
        member.setNickname(nickname);
        member.setMobile(registerVo.getPhone());
        member.setPassword(registerVo.getPassword());
        member.setIsDisabled(false);
        member.setAvatar("http://www.bskplu.buzz/image/BsKPLu.jpg");

        this.save(member);
    }

    @Override
    public User getByOpenid(String openId) {
        return baseMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getOpenid,openId));
    }
}
