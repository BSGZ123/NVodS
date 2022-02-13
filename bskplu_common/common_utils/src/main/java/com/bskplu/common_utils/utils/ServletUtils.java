package com.bskplu.common_utils.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @ClassName: ServletUtils
 * @Description: Servlet工具类
 * @Author BsKPLu
 * @Date 2022/2/13
 * @Version 1.1
 */
public class ServletUtils {
    /**
     * 获取RequestAttributes对象
     * @return
     */
    public static ServletRequestAttributes getRequestAttributes(){
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }

    /**
     * 获取reques对象
     * @return
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }


    /**
     * 获取response对象
     * @return
     */
    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }


    /**
     * 获取session对象
     * @return
     */
    public static HttpSession getSession(){
        return getRequest().getSession();
    }


    /**
     * 把字符串发送到用户浏览器
     * @param response
     * @param string
     * @return
     */
    public static String sendString(HttpServletResponse response, String string) {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
