package com.jiuzhou.service.impl;

import com.jiuzhou.common.properties.RepeatProperties;
import com.jiuzhou.common.constants.CommonConstants;
import com.jiuzhou.service.RepeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


/**
 * redis防重实现类 -->省平台集群情况下可配置为redis
 */
@Service(CommonConstants.REDIS)
public class RedisRepeatServiceImpl implements RepeatService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RepeatProperties repeatProperties;

    @Override
    public boolean process(String requestId) {
        Object o = redisTemplate.opsForValue().get(CommonConstants.REPEAT_REQ + requestId);
        if (null==o){
            redisTemplate.opsForValue().set(CommonConstants.REPEAT_REQ+requestId,repeatProperties.getCacheTime());
            return false;
        }
        return true;
    }
}
