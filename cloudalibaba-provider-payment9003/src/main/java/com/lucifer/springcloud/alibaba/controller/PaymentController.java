package com.lucifer.springcloud.alibaba.controller;

import com.lucifer.springcloud.entities.CommonResult;
import com.lucifer.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    public static HashMap<Long,Payment> hashMap = new HashMap<>();
    static {
        hashMap.put(1L,new Payment(1L,"7e542e914d0c437aa1d956044ef85505"));
        hashMap.put(2L,new Payment(2L,"4a17b7b25ac145828bc359910190eefd"));
        hashMap.put(3L,new Payment(3L,"1bd762a064d948aaa597e0ad20277b24"));
    }

    @GetMapping("paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id")Long id){
        Payment payment = hashMap.get(id);
        CommonResult<Payment> result = new CommonResult<>(200, "from mysql,serverPort: " + serverPort, payment);
        return result;
    }
}
