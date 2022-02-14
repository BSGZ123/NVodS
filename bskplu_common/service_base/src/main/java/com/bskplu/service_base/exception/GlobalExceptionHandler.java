package com.bskplu.service_base.exception;

import com.bskplu.common_utils.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName: GlobalExceptionHandler
 * @Description: 全局异常处理
 * @Author BsKPLu
 * @Date 2022/2/14
 * @Version 1.1
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 全局异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseResult error (Exception e) {
        log.error("全局异常处理:{}", e.getCause().getMessage());
        return ResponseResult.error("操作异常，请联系管理员修复~ : {" + e.getCause().getMessage() + "}");
    }


    /**
     * 业务出错处理
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseResult businessError (BusinessException e) {
        return ResponseResult.error().code(e.getCode()).message(e.getMsg());
    }
}
