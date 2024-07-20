/*
package com.pure.config;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.messaging.Message;

@Configuration
public class KafkaConfig {

    @Autowired
    ProducerFactory producerFactory;

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
       KafkaTemplate<String, Object> kafkaTemplate = new KafkaTemplate<String, Object>(producerFactory);
        kafkaTemplate.setProducerListener(new ProducerListener<String, Object>() {
            @Override
            public void onSuccess(ProducerRecord<String, Object> producerRecord, RecordMetadata recordMetadata) {
                System.out.println("发送成功 " + producerRecord.toString());
            }
            @Override
            public void onError(ProducerRecord<String, Object> producerRecord, RecordMetadata recordMetadata, Exception exception) {
                System.out.println("发送失败" + producerRecord.toString());
                System.out.println(exception.getMessage());
            }
        });
        return kafkaTemplate;
    }

    */
/**
     * 消费者异常处理器
     *//*

    @Bean
    public ConsumerAwareListenerErrorHandler consumerAwareErrorHandler() {
        return new ConsumerAwareListenerErrorHandler() {
            @Override
            public Object handleError(Message<?> message, ListenerExecutionFailedException exception, Consumer<?, ?> consumer) {
                System.out.println("消费异常：" + message.getPayload());
                return null;
            }
        };
    }
}

*/
