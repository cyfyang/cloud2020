package com.lucifer.springcloud.service;

import com.lucifer.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    public int save(Payment payment);

    public Payment getPaymentById(@Param("id")Long id);
}
