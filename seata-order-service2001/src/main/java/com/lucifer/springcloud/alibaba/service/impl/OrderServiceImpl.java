package com.lucifer.springcloud.alibaba.service.impl;

import com.lucifer.springcloud.alibaba.dao.OrderDao;
import com.lucifer.springcloud.alibaba.domain.Order;
import com.lucifer.springcloud.alibaba.service.AccountService;
import com.lucifer.springcloud.alibaba.service.OrderService;
import com.lucifer.springcloud.alibaba.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    OrderDao orderDao;

    @Autowired
    AccountService accountService;

    @Autowired
    StorageService storageService;

    @Override
    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)
    public void create(Order order) {
        log.info("------->开始新建订单");
        orderDao.create(order);

        log.info("------->订单微服务开始调用库存，做扣减");
        storageService.decrease(order.getProductId(),order.getCount());

        log.info("------->订单微服务开始调用账户余额，做扣减");
        accountService.decrease(order.getUserId(),order.getMoney());

        log.info("------->订单微服务开始调用账户余额，做扣减");
        orderDao.update(order.getUserId(),0);

        log.info("----->下订单结束了，O(∩_∩)O哈哈~");

    }
}
