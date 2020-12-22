package com.mod.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 描述当前类
 * @Author Mr.p Email:
 * @Date create in 2019/7/19 9:15
 */
public class JwtUtils{
    // 过期时间30分钟
    public static final long EXPIRE_TIME = 30 * 60 * 1000;

    /**
     * 1. 校验token是否正确
     *
     * @param token  密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token,String userName,String secret){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).withClaim("userName",userName).build();
            // 解析jwt
            DecodedJWT jwt = verifier.verify(token);
            return true;
        }catch(Exception exception){
            return false;
        }
    }

    /**
     *
     * @param token
     * @param secret
     * @return
     */
    public static boolean verify(String token,String secret){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            // 解析jwt
            DecodedJWT jwt = verifier.verify(token);
            return true;
        }catch(Exception exception){
            return false;
        }
    }


    /**
     * 2. 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getOpenid(String token){
        try{
            if(StringUtil.isEmpty(token)){
                return "";
            }
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("openid").asString();
        }catch(JWTDecodeException e){

            return "";
        }
    }
    public static Long getUserId(String token){
        try{
            if(StringUtil.isEmpty(token)){
                return 0L;
            }
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userId").asLong();
        }catch(JWTDecodeException e){
            return 0L;
        }
    }

    public static String getUserName(String token){
        if(StringUtil.isEmpty(token)){
            return "";
        }
        try{
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userName").asString();
        }catch(JWTDecodeException e){
            return "";
        }
    }

    /**
     * 3. 生成签名,30min后过期
     *
     * @param openid 用户名
     * @param secret 用户的密码
     * @return 加密的token
     */
    public static String sign(String openid,String userName,String secret){
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        Map<String,Object> map = new HashMap<String,Object>(3);
        map.put("alg","HS256");
        map.put("typ","JWT");
        // 附带openid信息
        return JWT.create().withHeader(map).withClaim("secret",secret).withClaim("openid",openid).withClaim("userName",userName).withIssuer("SERVICE").withSubject("this is test token").withNotBefore(new Date()).withAudience("APP")//签名的观众 也可以理解谁接受签名的
                .withExpiresAt(date).sign(algorithm);
    }

    /**
     * 生成签名
     *
     * @param userName
     * @param secret
     * @return
     */
    public static String sign(Long userId,String userName,String secret){
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        Map<String,Object> map = new HashMap<String,Object>(3);
        map.put("alg","HS256");
        map.put("typ","JWT");
        // 附带openid信息
        return JWT.create().withHeader(map).withClaim("secret",secret).withClaim("userId",userId).withClaim("userName",userName).withNotBefore(new Date()).withExpiresAt(date).sign(algorithm);
    }
}
