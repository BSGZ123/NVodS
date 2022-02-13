package com.bskplu.common_utils.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName: ResponseUtil
 * @Description: 响应实效 防止乱码
 * @Author BsKPLu
 * @Date 2022/2/13
 * @Version 1.1
 */
public class ResponseUtil {

    /**
     * 设定字符编码
     * @param response
     * @param responseResult
     */
    public static void out(HttpServletResponse response,ResponseResult responseResult){
        ObjectMapper mapper= new ObjectMapper();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);//未来可能弃用 编码问题依旧很严重欸
        try {
            mapper.writeValue(response.getWriter(),responseResult);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
