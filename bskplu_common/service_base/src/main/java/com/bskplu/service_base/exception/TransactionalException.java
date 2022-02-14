package com.bskplu.service_base.exception;

import com.bskplu.common_utils.utils.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName: TransactionalException
 * @Description: 连接状态异常
 * @Author BsKPLu
 * @Date 2022/2/14
 * @Version 1.1
 */
@EqualsAndHashCode
@Data
@AllArgsConstructor
public class TransactionalException extends RuntimeException{

    private Integer code;
    private String msg;

    public TransactionalException(String s) {
        super(s);
        this.code = ResultCode.ERROR;
        this.msg = s;
    }
}
