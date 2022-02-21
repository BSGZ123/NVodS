package com.bskplu.api_video.factory;

import com.bskplu.api_video.RemoteContentClient;
import com.bskplu.api_video.entity.vo.ContentWebVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @ClassName: RemoteContentClientFactory
 * @Description: RemoteContentClientFactory
 * @Author BsKPLu
 * @Date 2022/2/21
 * @Version 1.1
 */
@Component
public class RemoteContentClientFactory implements FallbackFactory<RemoteContentClient> {
    private static final Logger logger = LogManager.getLogger(RemoteContentClientFactory.class);
    @Override
    public RemoteContentClient create(Throwable cause) {
        logger.error("作品模块错误:{}", cause.getLocalizedMessage());
        return new RemoteContentClient() {
            @Override
            public ContentWebVO getContentInfoOrder(String id) {
                return null;
            }
        };
    }
}
