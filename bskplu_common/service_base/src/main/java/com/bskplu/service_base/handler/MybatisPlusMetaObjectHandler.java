package com.bskplu.service_base.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName: MybatisPlusMetaObjectHandler
 * @Description: 自动填充字段
 * @Author BsKPLu
 * @Date 2022/2/14
 * @Version 1.1
 */
@Component
@Slf4j
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {

    /**
     * 插入时填充字段数据 时间
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("字段数据填充配置");
        this.setFieldValByName("gmtCreate",new Date(),metaObject);
        this.setFieldValByName("gmtModified",new Date(),metaObject);
    }

    /**
     * 更新时添加时间数据
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("gmtModified",new Date(),metaObject);
    }
}
