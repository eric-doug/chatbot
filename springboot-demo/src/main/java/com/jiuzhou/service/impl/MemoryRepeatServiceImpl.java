package com.jiuzhou.service.impl;

import com.jiuzhou.common.properties.RepeatProperties;
import com.jiuzhou.common.constants.CommonConstants;
import com.jiuzhou.service.RepeatService;
import com.jiuzhou.utils.LocalCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 内存防重实现类 -->该类不适用集群 集群情况下请配置为其它实现类 特点单点性能好
 */
@Service(CommonConstants.MEMORY)
public class MemoryRepeatServiceImpl implements RepeatService {


    @Autowired
    private RepeatProperties repeatProperties;

    @Override
    public boolean process(String requestId) {

        String seq =  (String) LocalCache.CACHE.get(requestId);
        if(null==seq){
            //将请求id塞入缓存
            LocalCache.CACHE.put(requestId,requestId,repeatProperties.getCacheTime());
            return false;
        }

        return true;
    }
}
