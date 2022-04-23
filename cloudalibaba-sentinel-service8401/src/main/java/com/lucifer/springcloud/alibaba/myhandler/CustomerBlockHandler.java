package com.lucifer.springcloud.alibaba.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.lucifer.springcloud.entities.CommonResult;

public class CustomerBlockHandler {
    public CommonResult handlerException(BlockException exception){
        return new CommonResult(4444,"按客户自定义，global handlerException-------1");
    }

    public CommonResult handlerException2(BlockException exception){
        return new CommonResult(4444,"按客户自定义，global handlerException-------2");
    }
}
