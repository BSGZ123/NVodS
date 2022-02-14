package com.bskplu.service_base.exception;

import com.bskplu.common_utils.utils.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName: BusinessException
 * @Description: 业务异常
 * @Author BsKPLu
 * @Date 2022/2/14
 * @Version 1.1
 */

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class BusinessException extends RuntimeException{
    private Integer code;
    private String msg;

    public BusinessException(String s) {
        super(s);
        this.code = ResultCode.ERROR;
        this.msg = s;
    }
}
