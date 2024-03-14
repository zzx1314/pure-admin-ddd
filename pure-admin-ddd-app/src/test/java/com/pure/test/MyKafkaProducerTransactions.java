package com.pure.test;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @author ：jizhibing
 * @date ：Created in 2022/3/31
 * @description：
 */
public class MyKafkaProducerTransactions {

    private final static String KAFKA_SERVER_URL = "ip";

    public static void main(String[] args) throws Exception{
        //0 设置配置
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"10.66.0.251:9092");
        //选择序列化方式
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,  StringSerializer.class.getName());
        //指定事务id
        properties.setProperty(ProducerConfig.TRANSACTIONAL_ID_CONFIG,"transaction_id_01") ;

        //1 ：建立连接
        KafkaProducer<String,String> kafkaProducer = new KafkaProducer(properties);

        //a\ 初始化事务
        kafkaProducer.initTransactions();

        //b、开始事务
        kafkaProducer.beginTransaction();
        try{
            //2：发送消息
            kafkaProducer.send(new ProducerRecord("first", "hello"));
            //制造失败
            //int i = 1/0 ;

            //c、提交事务
            kafkaProducer.commitTransaction();
            System.out.println("success");
        }catch (Exception e){
            //d、回滚事务
            kafkaProducer.abortTransaction();
            System.out.println("fail");
        }finally {
            //3：关闭连接
            kafkaProducer.close();
        }

    }

}
