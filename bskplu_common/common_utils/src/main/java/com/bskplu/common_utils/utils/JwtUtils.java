package com.bskplu.common_utils.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @ClassName: JwtUtils
 * @Description: JWT验证Token
 * @Author BsKPLu
 * @Date 2022/2/13
 * @Version 1.1
 */
public class JwtUtils {
    /**
    服务器密钥设置
     */
    public static final String APP_SECRET ="bskplu";
    /**
    Token过期时间
     */
    public static final long EXPIRE =1000 * 60 * 60 * 24;

    /**
    根据id和昵称获取token
    带入id和name参数
     */
    public static String getJwtToken(String id,String nickname){


        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg","HS256")
                .setSubject(APP_SECRET + "_video")
                // 签发日期
                .setIssuedAt(new Date())
                // 过期时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                // 自定义参数
                .claim("id", id)
                .claim("nickname", nickname)
                // 加入密钥
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .compact();
    }

    /**
     判断token是否存在和有效
     @param jwtToken
     @return
     */
    public static boolean checkToken(String jwtToken) {
        if (StringUtils.isEmpty(jwtToken)) return false;
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     判断请求中token是否存在和有效
     */
    public static boolean checkToken(HttpServletRequest request) {
        String jwtToken = request.getHeader("token");
        return checkToken(jwtToken);
    }

    /**
     * 根据token获取id
     */
    public static String getUserIdByJwtToken(HttpServletRequest request){
        String jwtToken =request.getHeader("token");
        if (StringUtils.isEmpty(jwtToken)) return "";
        if (!checkToken(jwtToken)) return "";
        Jws<Claims> claimsJws =Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims=claimsJws.getBody();
        return (String) claims.get("id");
    }
}
