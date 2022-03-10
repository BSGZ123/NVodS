package com.bskplu.service_video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bskplu.api_video.entity.Content;
import com.bskplu.api_video.entity.vo.ContentVo;
import com.bskplu.api_video.entity.vo.ContentWebVO;
import com.bskplu.common_utils.utils.ResponseResult;

import java.util.List;

/**
 * @InterfaceName: ContentService
 * @Description: 作品服务接口
 * @Author BsKPLu
 * @Date 2022/3/9
 * @Version 1.1
 */
public interface ContentService extends IService<Content> {
    String addContentInfo(ContentVo categoryVo);

    ContentVo getContentWhitInfo(String id);

    boolean updateContentInfo(ContentVo categoryVo);

    /**
     * 获取内容预览
     * @param id
     * @return
     */
    ResponseResult getContentPreview(String id);

    /**
     * 发送作品内容
     * @param id
     * @return
     */
    ResponseResult sendContent(String id);

    /**
     *
     * @param contentVo
     * @return
     */
    ResponseResult getContentListPage(ContentVo contentVo);

    /**
     * 通过id删除内容
     * @param id
     * @return
     */
    ResponseResult deleteContentById(String id);

    /**
     * 根据作品ID获取作品的详细信息
     * @param id
     * @return
     */
    List<Content> selectByAuthorId(String id);

    /**
     *根据作品id获取该作品详细信息
     * @param contentId
     * @return
     */
    ContentWebVO selectContentDetailById(String contentId);

    /**
     * 更新播放信息
     * @param contentId
     * @return
     */
    ResponseResult updatePageViewCount(String contentId);


}
