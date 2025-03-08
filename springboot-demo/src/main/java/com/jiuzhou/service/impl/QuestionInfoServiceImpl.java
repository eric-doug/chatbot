package com.jiuzhou.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiuzhou.dao.QuestionInfoDao;
import com.jiuzhou.entity.QuestionInfo;
import com.jiuzhou.service.QuestionInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("questionInfoService")
public class QuestionInfoServiceImpl extends ServiceImpl<QuestionInfoDao, QuestionInfo> implements QuestionInfoService {
    Logger logger = LoggerFactory.getLogger(QuestionInfoServiceImpl.class);
    @Resource
    private QuestionInfoDao questionInfoDao;

    @Override
    public IPage page(Page<QuestionInfo> page, Wrapper<QuestionInfo> wrapper) {
        return questionInfoDao.selectPage(page, wrapper);
    }


}
