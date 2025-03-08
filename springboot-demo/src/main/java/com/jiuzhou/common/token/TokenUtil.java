package com.jiuzhou.common.token;

import com.alibaba.fastjson.JSON;
import com.jiuzhou.common.Enum.SecurityEnum;
import com.jiuzhou.common.Enum.UserEnums;
import com.jiuzhou.common.properties.JwtProperties;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 */
@Component
public class TokenUtil {

    private Logger logger = LoggerFactory.getLogger(TokenUtil.class);

    @Autowired
    private JwtProperties tokenProperties;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 构建token
     *
     * @param username 主体
     * @param claim    私有声明
     * @param longTerm 长时间特殊token 如：移动端，微信小程序等
     * @param userEnums 用户枚举
     * @return TOKEN
     */
    public Token createToken(String username, Object claim, boolean longTerm, UserEnums userEnums) {
        Token token = new Token();
        //访问token
        String accessToken = createToken(username, claim, tokenProperties.getTokenExpireTime());

        redisTemplate.opsForValue().set(CachePrefix.ACCESS_TOKEN.getPrefix(userEnums) + accessToken, 1,
                tokenProperties.getTokenExpireTime(), TimeUnit.MINUTES);
        //刷新token生成策略：如果是长时间有效的token（用于app），则默认15天有效期刷新token。如果是普通用户登录，则刷新token为普通token2倍数
        Long expireTime = longTerm ? 15 * 24 * 60L : tokenProperties.getTokenExpireTime() * 4; //设置8小时
        String refreshToken = createToken(username, claim, expireTime);

        redisTemplate.opsForValue().set(CachePrefix.REFRESH_TOKEN.getPrefix(userEnums) + refreshToken, 1, expireTime, TimeUnit.MINUTES);

        token.setAccessToken(accessToken);
        token.setRefreshToken(refreshToken);
        return token;
    }

    /**
     * 获取token信息
     * @param token
     * @return
     */
    public AuthUser getTokenInfo(String token){
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SecretKeyUtil.generalKeyByDecoders())
                    .parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
//            throw new ServiceException(ResultCode.USER_AUTH_EXPIRED.code()+"",ResultCode.USER_AUTH_EXPIRED.message());
        }
        if(null==claims){
            return null;
        }
        return JSON.toJavaObject(JSON.parseObject(claims.get(SecurityEnum.USER_CONTEXT.getValue()).toString()), AuthUser.class);
    }
    /**
     * 刷新token
     *
     * @param oldRefreshToken 刷新token
     * @param userEnums 用户枚举
     * @return token
     */
    public Token refreshToken(String oldRefreshToken, UserEnums userEnums) {

        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SecretKeyUtil.generalKeyByDecoders())
                    .parseClaimsJws(oldRefreshToken).getBody();
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
            //token 过期 认证失败等
            logger.debug("用户登录信息失效!");
//            throw new ServerException(ResultCode.USER_AUTH_EXPIRED.message());
        }

        //获取存储在claims中的用户信息
        String json = claims.get(SecurityEnum.USER_CONTEXT.getValue()).toString();
        AuthUser authUser = JSON.toJavaObject(JSON.parseObject(json), AuthUser.class);


        String username = authUser.getUserName();
        //获取是否长期有效的token
        boolean longTerm = authUser.getLongTerm();
        //如果缓存中有刷新token &&
        if ( null!=redisTemplate.opsForValue().get(CachePrefix.REFRESH_TOKEN + ":" + UserEnums.MEMBER+":" + oldRefreshToken)) {
            Token token = new Token();
            //访问token
            String accessToken = createToken(username, authUser, tokenProperties.getTokenExpireTime());
            redisTemplate.opsForValue().set(CachePrefix.ACCESS_TOKEN.getPrefix(userEnums) + accessToken, 1, tokenProperties.getTokenExpireTime(), TimeUnit.MINUTES);

            //如果是信任登录设备，则刷新token长度继续延长
            Long expirationTime = tokenProperties.getTokenExpireTime() * 2;
            if (longTerm) {
                expirationTime = 60 * 24 * 15L;
            }

            //刷新token生成策略：如果是长时间有效的token（用于app），则默认15天有效期刷新token。如果是普通用户登录，则刷新token为普通token2倍数
            String refreshToken = createToken(username, authUser, expirationTime);

            redisTemplate.opsForValue().set(CachePrefix.REFRESH_TOKEN.getPrefix(userEnums) + refreshToken, 1, expirationTime, TimeUnit.MINUTES);
            token.setAccessToken(accessToken);
            token.setRefreshToken(refreshToken);
            redisTemplate.delete(CachePrefix.REFRESH_TOKEN.getPrefix(userEnums) + oldRefreshToken);
            return token;
        } else {
            throw new RuntimeException(ResultCode.USER_CONNECT_LOGIN_ERROR.message());
        }

    }

    /**
     * 生成token
     *
     * @param username       主体
     * @param claim          私有神明内容
     * @param expirationTime 过期时间（分钟）
     * @return token字符串
     */
    private String createToken(String username, Object claim, Long expirationTime) {
        //JWT 生成
        return Jwts.builder()
                //jwt 私有声明
                .claim(SecurityEnum.USER_CONTEXT.getValue(), JSON.toJSONString(claim))
                //JWT的主体
                .setSubject(username)
                //失效时间 当前时间+过期分钟
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime * 60 * 1000))
                //签名算法和密钥
                .signWith(SecretKeyUtil.generalKey())
                .compact();
    }
}
