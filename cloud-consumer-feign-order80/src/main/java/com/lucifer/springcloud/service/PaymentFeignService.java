package com.lucifer.springcloud.service;

import com.lucifer.springcloud.entities.CommonResult;
import com.lucifer.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE" )
public interface PaymentFeignService {
    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id")Long id);

    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeout();
}
