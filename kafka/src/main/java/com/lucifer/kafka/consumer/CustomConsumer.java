package com.lucifer.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 消费一个主题
 */
public class CustomConsumer {
    public static void main(String[] args) {

        //0、配置
        Properties properties = new Properties();

        // 连接bootstrap.servers
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"hadoop102:9092,hadoop103:9092");

        //反序列化
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());

        //配置消费者组id
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"test");

        //1、创建一个消费者 "","hello"
        KafkaConsumer<String,String> consumer = new KafkaConsumer<>(properties);

        //2、订阅主题first
        List<String> topics = new ArrayList<>();
        topics.add("first");
        consumer.subscribe(topics);

        //3、消费数据
        while (true){
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(record);
            }
        }
    }
}
