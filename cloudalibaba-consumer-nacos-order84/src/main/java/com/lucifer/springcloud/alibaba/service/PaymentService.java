package com.lucifer.springcloud.alibaba.service;

import com.lucifer.springcloud.alibaba.service.fallback.PaymentFallbackService;
import com.lucifer.springcloud.entities.CommonResult;
import com.lucifer.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "nacos-payment-provider",fallback = PaymentFallbackService.class)
public interface PaymentService {
    @GetMapping("paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id);

}
