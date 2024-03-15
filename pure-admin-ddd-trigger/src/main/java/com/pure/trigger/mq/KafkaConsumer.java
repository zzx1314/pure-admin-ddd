package com.pure.trigger.mq;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KafkaConsumer {
    /**
     * 普通的消费者
     */
   /* @KafkaListener(topics = {"sb_topic"})
    public void onNormalMessage(ConsumerRecord<String, Object> record) {
        System.out.println("简单消费：" + record.topic() + "-" + record.partition() + "=" +
                record.value());
    }*/

    /**
     * 批量消费
     */
  /*  @KafkaListener(id = "consumer2", topics = {"sb_topic"}, groupId = "sb_group")
    public void onBatchMessage(List<ConsumerRecord<String, Object>> records) {
        System.out.println(">>> 批量消费一次，recoreds.size()=" + records.size());
        for (ConsumerRecord<String, Object> record : records) {
            System.out.println(record.value());
        }
    }*/


    // 将这个异常处理器的BeanName放到@KafkaListener注解的errorHandler属性里面
    /*@KafkaListener(topics = {"sb_topic"},errorHandler = "consumerAwareErrorHandler")
    public void onMessage4(ConsumerRecord<?, ?> record) throws Exception {
        throw new Exception("简单消费-模拟异常");
    }*/

    // 批量消费也一样，异常处理器的message.getPayload()也可以拿到各条消息的信息
    @KafkaListener(topics = "sb_topic",errorHandler="consumerAwareErrorHandler")
    public void onMessage5(List<ConsumerRecord<?, ?>> records) throws Exception {
        System.out.println("批量消费一次...");
        throw new Exception("批量消费-模拟异常");
    }


}
