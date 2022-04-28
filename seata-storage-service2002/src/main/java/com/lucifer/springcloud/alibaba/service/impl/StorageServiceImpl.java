package com.lucifer.springcloud.alibaba.service.impl;

import com.lucifer.springcloud.alibaba.dao.StorageDao;
import com.lucifer.springcloud.alibaba.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class StorageServiceImpl implements StorageService {
    @Resource
    StorageDao storageDao;

    @Override
    public void decrease(Long productId, Integer count) {
        storageDao.decrease(productId,count);
    }
}
