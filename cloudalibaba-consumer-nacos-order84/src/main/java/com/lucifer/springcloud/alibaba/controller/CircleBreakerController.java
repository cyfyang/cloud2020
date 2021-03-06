package com.lucifer.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.lucifer.springcloud.alibaba.service.PaymentService;
import com.lucifer.springcloud.entities.CommonResult;
import com.lucifer.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("consumer")
public class CircleBreakerController {
    public static final String SERVICE_URL = "http://nacos-payment-provider";

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("fallback/{id}")
//    @SentinelResource("fallback")//什么都没配
//    @SentinelResource(value = "fallback" ,fallback = "handlerFallback") //fallback只负责业务异常
//    @SentinelResource(value = "fallback",blockHandler = "blockHandler") // blockHandler只负责sentinel控制台配置违规
    @SentinelResource(value="fallback",fallback = "handlerFallback",blockHandler = "blockHandler",exceptionsToIgnore = {IllegalArgumentException.class})
    public CommonResult<Payment> fallback(@PathVariable Long id){
        CommonResult result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id, CommonResult.class, id);

        if(id == 4){
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常。。。。。");
        } else if (result.getData() == null){
            throw new NullPointerException("NullPointerException,该ID没有对应记录，空指针异常");
        }
        return result;
    }

    //本例是fallback
    public CommonResult<Payment> handlerFallback(Long id,Throwable e){
        Payment payment = new Payment(id,null);
        return new CommonResult<>(444,"兜底异常handlerFallback，exception内容" + e.getMessage(),payment);
    }

    //本例是blockHandler
    public CommonResult<Payment> blockHandler(Long id, BlockException exception){
        Payment payment = new Payment(id,null);
        return new CommonResult<>(445,"blockHandler-sentinel限流，无此流水：blockException " + exception.getMessage(),payment);
    }

    //=================openFeign===============

    @Autowired
    private PaymentService paymentService;

    @GetMapping("paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id")Long id){
        return paymentService.paymentSQL(id);
    }
}
