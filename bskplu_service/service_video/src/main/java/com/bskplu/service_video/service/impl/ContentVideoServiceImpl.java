package com.bskplu.service_video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bskplu.api_video.entity.ContentVideo;
import com.bskplu.api_video.entity.vo.ContentVideoInfoVO;
import com.bskplu.api_vod.VodClient;
import com.bskplu.common_utils.utils.ResponseResult;
import com.bskplu.common_utils.utils.ResultCode;
import com.bskplu.service_base.exception.BusinessException;
import com.bskplu.service_base.utils.text.StringUtils;
import com.bskplu.service_video.mapper.ContentVideoMapper;
import com.bskplu.service_video.service.ContentVideoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: ContentVideoServiceImpl
 * @Description: 作品小结视频服务实现类
 * @Author BsKPLu
 * @Date 2022/3/10
 * @Version 1.1
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ContentVideoServiceImpl extends ServiceImpl<ContentVideoMapper, ContentVideo> implements ContentVideoService {

    private final VodClient vodClient;

    @Override
    //获得小节章节数
    public boolean getCountByChapterId(String id) {
        final LambdaQueryWrapper<ContentVideo> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(ContentVideo::getChapterId,id);
        return baseMapper.selectCount(wrapper)>0;
    }

    /**
     * 从前端获取vo添加保存到数据库
     * @param contentVideoVO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addContentVideo(ContentVideoInfoVO contentVideoVO) {
        final ContentVideo contentVideo=new ContentVideo();
        BeanUtils.copyProperties(contentVideoVO,contentVideo);
        return this.save(contentVideo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateContentVideo(ContentVideoInfoVO contentVideoInfoVO) {
        final ContentVideo contentVideo=new ContentVideo();
        BeanUtils.copyProperties(contentVideoInfoVO,contentVideo);
        return this.updateById(contentVideo);
    }

    @Override
    public boolean deleteContentVideoWithById(String id) {
        //根据id获取必要信息
        final ContentVideo contentVideo=this.baseMapper.selectById(id);
        final String videoSourceId=contentVideo.getVideoSourceId();

        if(!StringUtils.isEmpty(videoSourceId)){
            final ResponseResult responseResult=vodClient.removeVideo(videoSourceId);
            if(!responseResult.getSuccess()){
                throw new BusinessException(ResultCode.ERROR,"调用删除远程视频失败！");
            }
            log.info("小节视频删除成功:"+responseResult);
        }else{
            log.error("视频资源不存在！视频删除失败！:{0}"+id);
        }
        return this.baseMapper.deleteById(id) > 0;
    }

    /**
     * 根据视频内容id删除指定内容
     * @param id
     * @return
     */
    @Override
    public boolean deleteContentVideoWithByContentId(String id) {
        QueryWrapper<ContentVideo> wrapper=new QueryWrapper<>();
        wrapper.eq("content_id",id);
        return baseMapper.delete(wrapper)>0;
    }
}
