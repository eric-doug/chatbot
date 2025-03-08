package com.jiuzhou.intercepter;

import com.jiuzhou.common.annotation.Repeat;
import com.jiuzhou.common.properties.RepeatProperties;
import com.jiuzhou.service.RepeatService;
import com.jiuzhou.utils.WebUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 【问题】群组表
 * github地址 http://www.github.com/wanyushu
 * gitee地址 http://www.gitee.com/wanyushu
 *
 * @author yushu
 * @email 921784721@qq.com
 * @date 2023/12/29 13:43
 */
@Component
public class RepeatInterceptor implements HandlerInterceptor {


    private Logger logger = LoggerFactory.getLogger(RepeatInterceptor.class);

    @Autowired
    private RepeatProperties repeatProperties;

    @Autowired
    private ApplicationContext applicationContext;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Repeat annotation = null;
        if(handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(Repeat.class);
        }
        if(null==annotation){
            return true;
        }

        //判断是否设置了白名单
        if(CollectionUtils.isNotEmpty(repeatProperties.getWhiteIp())
                &&!repeatProperties.getWhiteIp().contains(WebUtils.getIP())){
            return false;
        }
        //走拦截链路
        Object bean = applicationContext.getBean(repeatProperties.getType());

        if(null==bean || !(bean instanceof RepeatService)){
            logger.error("请求repeat拦截方式没有配置!");
            return false;
        }

        String reqeustId = request.getHeader(repeatProperties.getRequestId());
        if(null==reqeustId){
            logger.warn("请求链路id为设置");
            return false;
        }

        return !((RepeatService) bean).process(reqeustId);
    }
}
