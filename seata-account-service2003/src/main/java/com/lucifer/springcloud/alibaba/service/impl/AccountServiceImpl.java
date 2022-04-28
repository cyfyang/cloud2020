package com.lucifer.springcloud.alibaba.service.impl;

import com.lucifer.springcloud.alibaba.dao.AccountDao;
import com.lucifer.springcloud.alibaba.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    AccountDao accountDao;
    @Override
    public void decrease(Long userId, BigDecimal money) {
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        accountDao.decrease(userId,money);
    }
}
