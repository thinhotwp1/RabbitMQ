package com.example.rabbitmq.springrabbitmqconsumer.controller;


import com.example.rabbitmq.springrabbitmqconsumer.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import static com.example.rabbitmq.springrabbitmqconsumer.configuration.RabbitMQConfiguration.*;

@Component
@Slf4j
public class Consumer {

    // Header Direct, Fanout, Topic


    @RabbitListener(queues = "queue.A")
    private void receiveFromA(Message message){
        log.info("Message recevied from QUEUEA->{}",message);
    }
    @RabbitListener(queues = "queue.C")
    private void receiveFromC(Message message){
        log.info("Message recevied from QUEUEC->{}",message);
    }

    @RabbitListener(queues = "queue.B")
    private void receiveFromB(Message message){
        log.info("Message recevied from QUEUEB->{}",message);
    }
//    @RabbitListener(queues = "queue.all")
//    private void receiveFromAll(Message message){
//        log.info("Message recevied from ALL_QUEUE->{}",message);
//    }


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
