package com.jiuzhou.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiuzhou.dao.UserAccountDao;
import com.jiuzhou.entity.UserAccount;
import com.jiuzhou.service.UserAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service("userAccountService")
public class UserAccountServiceImpl extends ServiceImpl<UserAccountDao, UserAccount> implements UserAccountService {

    private final static Logger logger = LoggerFactory.getLogger(UserAccountServiceImpl.class);


}
