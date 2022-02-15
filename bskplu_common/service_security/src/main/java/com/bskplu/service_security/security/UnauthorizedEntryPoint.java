package com.bskplu.service_security.security;

import com.bskplu.common_utils.utils.ResponseResult;
import com.bskplu.common_utils.utils.ResponseUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName: UnauthorizedEntryPoint
 * @Description: 未授权接入点处理
 * @Author BsKPLu
 * @Date 2022/2/15
 * @Version 1.1
 */

public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {
    /**
     * 失败的授权
     * @param httpServletRequest
     * @param httpServletResponse
     * @param e
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        e.printStackTrace();
        ResponseUtil.out(httpServletResponse, ResponseResult.error(e.getLocalizedMessage()));
    }
}
