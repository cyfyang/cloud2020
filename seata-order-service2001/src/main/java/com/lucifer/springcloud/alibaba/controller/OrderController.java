package com.lucifer.springcloud.alibaba.controller;

import com.lucifer.springcloud.alibaba.domain.CommonResult;
import com.lucifer.springcloud.alibaba.domain.Order;
import com.lucifer.springcloud.alibaba.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {
    @Resource
    private OrderService orderService;

    @GetMapping("/order/create")
    public CommonResult create(Order order){
        orderService.create(order);
        return new CommonResult(200,"订单创建成功");
    }
}
