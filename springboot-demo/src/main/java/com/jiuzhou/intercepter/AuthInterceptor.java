/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.jiuzhou.intercepter;


import com.jiuzhou.common.annotation.Login;
import com.jiuzhou.common.constants.CommonConstants;
import com.jiuzhou.common.token.AuthUser;
import com.jiuzhou.common.token.TokenUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * github地址 http://www.github.com/wanyushu
 * gitee地址 http://www.gitee.com/wanyushu
 * @author yushu
 * @email 921784721@qq.com
 **/
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {


    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        Login annotation;
        if(handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(Login.class);
        }else{
            return true;
        }
        //从header中获取token
        String token = request.getHeader("token");
        //如果header中不存在token，则从参数中获取token
        if(StringUtils.isBlank(token)){
            token = request.getParameter("token");
        }

        if(null==token){
            return false;
        }
        AuthUser authUser = tokenUtil.getTokenInfo(token);
        //设置userId到request里，后续根据userId，获取用户信息
        request.setAttribute(CommonConstants.USER_KEY, authUser.getId());

        return true;
    }
}
