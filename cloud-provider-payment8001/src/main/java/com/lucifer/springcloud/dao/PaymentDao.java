package com.lucifer.springcloud.dao;

import com.lucifer.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {
    public int save(Payment payment);

    public Payment getPaymentById(@Param("id")Long id);
}
