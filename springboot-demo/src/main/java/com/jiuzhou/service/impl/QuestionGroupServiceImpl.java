package com.jiuzhou.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiuzhou.dao.QuestionGroupDao;
import com.jiuzhou.entity.QuestionGroup;
import com.jiuzhou.service.QuestionGroupService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("questionGroupService")
public class QuestionGroupServiceImpl extends ServiceImpl<QuestionGroupDao, QuestionGroup> implements QuestionGroupService {

    @Resource
    private QuestionGroupDao questionGroupMapper;

    @Override
    public IPage page(Page<QuestionGroup> page, Wrapper<QuestionGroup> wrapper) {
        return questionGroupMapper.selectPage(page, wrapper);
    }
}
