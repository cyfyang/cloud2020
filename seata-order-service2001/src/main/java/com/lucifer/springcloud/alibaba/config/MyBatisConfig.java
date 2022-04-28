package com.lucifer.springcloud.alibaba.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.lucifer.springcloud.alibaba.dao"})
public class MyBatisConfig {
}
