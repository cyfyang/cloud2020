package com.lucifer.boot.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import sun.applet.Main;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class IDGeneratorSnowflake {
    private long workerId = 0;
    private long datacenterId = 1;

    private Snowflake snowflake = IdUtil.createSnowflake(workerId, datacenterId);

    /**
     * post构造完开始执行，加载一些初始化的工作
     */
    @PostConstruct
    public void init(){
        try {
            workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
            log.info("当前机器的workerId:{}",workerId);
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("当前机器的workerId获取失败",e);
            workerId = NetUtil.getLocalhostStr().hashCode();
        }
    }

    public synchronized long snowflakeId(){
        return snowflake.nextId();
    }

    public synchronized long snowflakeId(long workerId, long datacenterId){
        Snowflake snowflake = IdUtil.createSnowflake(workerId,datacenterId);
        return snowflake.nextId();
    }

    /**
     * 生成的是不带-的字符串，类似于：015a66e39be242288d6913ad3db01607
     * @return
     */
    public String simpleUUID(){
        return IdUtil.simpleUUID();
    }

    /**
     * 生成的UUID是带-的字符串，类似于：632033bc-730d-4792-9385-7102b1e58928
     * @return
     */
    public String randomUUID(){
        return IdUtil.randomUUID();
    }

    public static void main(String[] args) {
        System.out.println(new IDGeneratorSnowflake().randomUUID());
        System.out.println(new IDGeneratorSnowflake().simpleUUID());
    }

}
