/*
package com.pure.trigger.mq;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class kafkaProducer {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @PostConstruct
    public void init() {
        // 确保KafkaTemplate具有事务支持
        if (!kafkaTemplate.isTransactional()) {
            throw new IllegalStateException("KafkaTemplate is not configured for transactions");
        }
    }

    */
/**
     * 简单的生产泽
     *//*

    @GetMapping("/kafka/normal/{message}")
    public void sendNormalMessage(@PathVariable("message") String message) {
        kafkaTemplate.send("sb_topic", message);
    }

    */
/**
     * 生产者回调的第一种写法
     *//*

    @GetMapping("/kafka/callbackOne/{message}")
    public void sendCallbackOneMessage(@PathVariable("message") String message) {
        kafkaTemplate.send("sb_topic", message).addCallback(new SuccessCallback<SendResult<String, Object>>() {
            //成功的回调
            @Override
            public void onSuccess(SendResult<String, Object> success) {
                // 消息发送到的topic
                String topic = success.getRecordMetadata().topic();
                // 消息发送到的分区
                int partition = success.getRecordMetadata().partition();
                // 消息在分区内的offset
                long offset = success.getRecordMetadata().offset();
                System.out.println("发送消息成功1:" + topic + "-" + partition + "-" + offset);
            }
        }, new FailureCallback() {
            //失败的回调
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("发送消息失败1:" + throwable.getMessage());
            }
        });
    }

    */
/**
     * 回调的第二种写法
     *//*

    @GetMapping("/kafka/callbackTwo/{message}")
    public void sendCallbackTwoMessage(@PathVariable("message") String message) {
        kafkaTemplate.send("sb_topic", message).addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("发送消息失败2："+throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                System.out.println("发送消息成功2：" + result.getRecordMetadata().topic() + "-"
                        + result.getRecordMetadata().partition() + "-" + result.getRecordMetadata().offset());
            }
        });
    }

    */
/**
     * 生产者事务
     * @param message
     *//*

    @GetMapping("/kafka/transaction/{message}")
    public void sendTransactionMessage(@PathVariable("message") String message) {
        //声明事务：后面报错消息不会发出去
        kafkaTemplate.executeInTransaction(new KafkaOperations.OperationsCallback<String, Object, Object>() {
            @Override
            public Object doInOperations(KafkaOperations<String, Object> operations) {
                */
/*List<String> messages = List.of("hello", "world", "spring", "kafka", "message");
                for (String one:messages){
                    operations.send("sb_topic", one);
                }*//*

                //throw new RuntimeException("fail");
                return true;
            }
        });
        // //不声明事务：后面报错但前面消息已经发送成功了
        // kafkaTemplate.send("sb_topic", message + " test executeInNoTransaction");
        // throw new RuntimeException("fail");
    }




}
*/
