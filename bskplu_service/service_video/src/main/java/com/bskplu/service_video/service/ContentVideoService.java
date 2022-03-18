package com.bskplu.service_video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bskplu.api_video.entity.ContentVideo;
import com.bskplu.api_video.entity.vo.ContentVideoInfoVO;

/**
 * @InterfaceName: ContentVideoService
 * @Description: 作品视频服务接口类
 * @Author BsKPLu
 * @Date 2022/3/9
 * @Version 1.1
 */
public interface ContentVideoService extends IService<ContentVideo> {
    /**
     * 根据作品id获取作品章节数
     * @param id
     * @return
     */
    boolean getCountByChapterId(String id);

    /**
     * 添加视频内容
     * @param contentVideoVO
     * @return
     */
    boolean addContentVideo(ContentVideoInfoVO contentVideoVO);

    /**
     * 更新视频内容
     * @param contentVideoInfoVO
     * @return
     */
    boolean updateContentVideo(ContentVideoInfoVO contentVideoInfoVO);

    /**
     * 删除视频内容
     * @param id
     * @return
     */
    boolean deleteContentVideoWithById(String id);

    /**
     * 根据内容id删除视频内容
     * @param id
     * @return
     */
    boolean deleteContentVideoWithByContentId(String id);


}
