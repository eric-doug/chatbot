package com.jiuzhou.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jiuzhou.entity.QuestionInfo;
import com.jiuzhou.entity.UserInfo;
import com.jiuzhou.service.QuestionInfoService;
import com.jiuzhou.utils.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 【问题】问题信息表
 * github地址 http://www.github.com/wanyushu
     * gitee地址 http://www.gitee.com/wanyushu
 * @author yushu
 * @email 
 * @date 2023-04-27 18:00:53
 */
@Api(tags = "【问题】问题信息表controller",value= "【问题】问题信息表相关接口")
@RestController
@RequestMapping("/question")
public class QuestionInfoController {


    @Autowired
    private QuestionInfoService questionInfoService;

    /**
     * 用户提问接口
     */
    @ApiOperation(value = "用户提问接口")
    @PostMapping("ask")
    public RestResult ask(UserInfo userInfo){
//        return questionInfoService.askQuestion(userInfo,questionDto);
        return RestResult.ok();
    }

    /**
     * 查询上下文根据id
     */
    @ApiOperation(value = "根据问题组查询对话上下文")
    @PostMapping("getContextById/{groupId}")
    public RestResult getContextById(@RequestHeader("userId")Long userId, @PathVariable("groupId") Integer groupId){
        List<QuestionInfo> list = questionInfoService.list(Wrappers.<QuestionInfo>lambdaQuery()
                .eq(QuestionInfo::getGroupId, groupId)
                .eq(QuestionInfo::getUserId, userId)
        );
        return RestResult.ok(list);
    }


    /**
     * 查询上下文根据id
     */
    @ApiOperation(value = "根据消息id查询消息详情")
    @PostMapping("getMessage/{msgId}")
    public RestResult getMessageById(@PathVariable("msgId") Integer msgId){
        QuestionInfo byId = questionInfoService.getById(msgId);
        return RestResult.ok(byId);
    }

    /**
     * 查询上下文根据id
     */
    @ApiOperation(value = "删除对应的问答")
    @GetMapping("deleteById/{id}")
    public RestResult delete( @PathVariable("id") Integer msgId){
        QuestionInfo questionInfo = questionInfoService.getById(msgId);
        if(null==questionInfo){
            return RestResult.failed("操作异常");
        }
        questionInfoService.removeById(questionInfo);
        return RestResult.ok();
    }



}
