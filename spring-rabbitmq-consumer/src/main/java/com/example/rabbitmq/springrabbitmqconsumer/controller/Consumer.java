package com.example.rabbitmq.springrabbitmqconsumer.controller;


import com.example.rabbitmq.springrabbitmqconsumer.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer {
    @RabbitListener(queues = "queue.A")
    private void receiveFromA(Message message){
        log.info("Message recevied from QUEUEA->{}",message);
    }

    @RabbitListener(queues = "queue.B")
    private void receiveFromB(Message message){
        log.info("Message recevied from QUEUEB->{}",message);
    }
    @RabbitListener(queues = "queue.all")
    private void receiveFromAll(Message message){
        log.info("Message recevied from ALL_QUEUE->{}",message);
    }

    // Header Exchange
//    @RabbitListener(queues = "queue.A")
//    private void receiveFromA(String message){
//        log.info("Message recevied from QUEUEA->{}",message);
//    }
//
//    @RabbitListener(queues = "queue.B")
//    private void receiveFromB(String message){
//        log.info("Message recevied from QUEUEB->{}",message);
//    }
//    @RabbitListener(queues = "queue.all")
//    private void receiveFromAll(String message){
//        log.info("Message recevied from ALL_QUEUE->{}",message);
//    }
}
