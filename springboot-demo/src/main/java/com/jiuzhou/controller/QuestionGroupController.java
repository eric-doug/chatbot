package com.jiuzhou.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiuzhou.common.annotation.Decrypt;
import com.jiuzhou.common.annotation.Encrypt;
import com.jiuzhou.common.annotation.Repeat;
import com.jiuzhou.entity.QuestionGroup;
import com.jiuzhou.service.QuestionGroupService;
import com.jiuzhou.utils.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 【问题】群组表
 * github地址 http://www.github.com/wanyushu
 * gitee地址 http://www.gitee.com/wanyushu
 * @author yushu
 * @email 
 * @date 2023-04-27 18:00:53
 */
@Api(tags = "【问题】群组表controller",value= "【问题】群组表相关接口")
@RestController
@RequestMapping("questionGroup")
public class QuestionGroupController {

    @Autowired
    private QuestionGroupService questionGroupService;


    /**
     * 根据id删除对应的问题组
     */

    @Encrypt
    @Decrypt
//    @Repeat
    @ApiOperation(value = "新增聊天组")
    @PostMapping("save")
    public RestResult save(@RequestBody QuestionGroup questionGroup){
        if(StringUtils.isEmpty(questionGroup.getGroupName())){
            return  RestResult.failed("新增聊天组名不能为空");
        }
        questionGroup.setCreateTime(new Date());
        questionGroup.setIsDel(0);
        System.out.println("入参情况打印:"+JSON.toJSONString(questionGroup));
        return RestResult.ok();
    }


}
