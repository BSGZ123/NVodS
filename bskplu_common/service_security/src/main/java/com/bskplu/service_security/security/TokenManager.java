package com.bskplu.service_security.security;

import com.bskplu.common_utils.constant.Constants;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @ClassName: TokenManager
 * @Description: token管理器
 * @Author BsKPLu
 * @Date 2022/2/15
 * @Version 1.1
 */
public class TokenManager {

    /**
     * 以用户名创建token
     * @param username
     * @return
     */
    public String createToken(String username){
        return Jwts.builder()
                .setSubject(username)//主题(用户名)
                .setExpiration(new Date(System.currentTimeMillis()+ Constants.TOKEN_EXPIRATION_TIME))//有效时长
                .signWith(SignatureAlgorithm.HS512,Constants.TOKEN_SECRET_KEY)//签发
                .compressWith(CompressionCodecs.GZIP)//压缩
                .compact();
    }

    public String getUserFromToken(String token){
        return Jwts.parser()
                .setSigningKey(Constants.TOKEN_SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();//获取用户名
    }


}
