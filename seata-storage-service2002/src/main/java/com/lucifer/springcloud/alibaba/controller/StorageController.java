package com.lucifer.springcloud.alibaba.controller;

import com.lucifer.springcloud.alibaba.domain.CommonResult;
import com.lucifer.springcloud.alibaba.service.StorageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("storage")
public class StorageController {
    @Resource
    private StorageService storageService;

    @PostMapping("decrease")
    public CommonResult decrease(Long productId,Integer count){
        storageService.decrease(productId,count);
        return new CommonResult<>(200,"扣减库存成功");
    }
}
