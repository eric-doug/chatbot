package com.jiuzhou.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jiuzhou.common.Enum.UserEnums;
import com.jiuzhou.common.annotation.Login;
import com.jiuzhou.common.annotation.LoginUser;
import com.jiuzhou.common.token.AuthUser;
import com.jiuzhou.common.token.Token;
import com.jiuzhou.common.token.TokenUtil;
import com.jiuzhou.common.vo.UserVo;
import com.jiuzhou.entity.UserInfo;
import com.jiuzhou.service.UserAccountService;
import com.jiuzhou.service.UserInfoService;
import com.jiuzhou.utils.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * github地址 http://www.github.com/wanyushu
 * gitee地址 http://www.gitee.com/wanyushu
 * @author yushu
 * @email 921784721@qq.com
 **/
@Api(tags = "【用户】信息表controller",value= "【用户】信息表相关接口")
@RestController
@RequestMapping("user")
public class UserInfoController {

    private Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private TokenUtil tokenUtil;

    /**
     * @return
     */

    @Login
    @ApiOperation(value = "用户登录接口")
    @PostMapping("login")
    public RestResult login(@RequestBody UserVo userVo)  {
        logger.info("用户登录:{}",userVo);
        UserInfo userInfo = userInfoService.getOne(Wrappers.<UserInfo>lambdaQuery()
                .eq(UserInfo::getEmail, userVo.getAccount()));
        AuthUser authUser = new AuthUser(null,userInfo.getUserId(),userInfo.getUserName(),userInfo.getAvatar());
        Token token = tokenUtil.createToken(userInfo.getUserName(), authUser, false, UserEnums.MEMBER);
        return RestResult.ok(token);
    }

    /**
     * @return
     */
    @ApiOperation(value = "查询用户信息")
    @GetMapping("info")
    public RestResult login(@LoginUser UserInfo userInfo)  {
        logger.info("用户获取登录信息:{}",userInfo);
        return RestResult.ok(userInfo);
    }

}
