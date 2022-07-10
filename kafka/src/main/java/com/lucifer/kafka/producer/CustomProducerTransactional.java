package com.lucifer.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * 异步发送  事物
 */
public class CustomProducerTransactional {
    public static void main(String[] args) {
        // 0.配置
        Properties properties = new Properties();
        // 连接集群 bootstrap.servers
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"hadoop102:9092,hadoop103:9092");

        //指定对应的key和value的序列化类型
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //必须指定事物id,且唯一
        properties.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG,"transactional_id_001");
        // 1.创建kafka生产者对象
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);

        //初始化事物
        kafkaProducer.initTransactions();

        //开始事物
        kafkaProducer.beginTransaction();
        try {
            // 2.发送数据
            for (int i = 0; i < 5; i++) {
                kafkaProducer.send(new ProducerRecord<>("first","hello kafka "+i));
            }
            //提交事务
            kafkaProducer.commitTransaction();
        } catch (Exception e) {
            //终止事物
            kafkaProducer.abortTransaction();
        } finally {
            // 3.关闭资源
            kafkaProducer.close();
        }

    }
}
