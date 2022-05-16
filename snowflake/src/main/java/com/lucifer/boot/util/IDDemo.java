package com.lucifer.boot.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IDDemo {
    public static void main(String[] args) {
        System.out.println("11111111111111111111111111111111111111111".length());

        long time = 2199023255551L;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(format.format(new Date(time)));

        System.out.println(".....................雪花算法秀一波........................");
        IDGeneratorSnowflake snowflake = new IDGeneratorSnowflake();

        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0;i<20;i++){
            executor.submit(() ->{
                System.out.println(snowflake.snowflakeId()+"    "+Thread.currentThread());
            });
        }

         executor.shutdown();
    }
}
