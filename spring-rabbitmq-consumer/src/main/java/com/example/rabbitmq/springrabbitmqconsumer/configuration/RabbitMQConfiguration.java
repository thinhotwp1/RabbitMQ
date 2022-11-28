package com.example.rabbitmq.springrabbitmqconsumer.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class RabbitMQConfiguration {

    // Khai báo các object
    public static final String QUEUE_A = "queue.A";
    public static final String QUEUE_C = "queue.C";
    public static final String QUEUE_B = "queue.B";
    //    public static final String ALL_QUEUE = "queue.all";
    public static final String ROUTING_A = "routing.A";
    public static final String ROUTING_B = "routing.B";
    public static final String ROUTING_C = "routing.C";
//    public static final String ALL_ROUTING = "routing.*";

    public static final String EMAIL_EXCHANGE = "exchange.tsdc.email";
    public static final String HOC_SINH_EXCHANGE = "exchange.tsdc.hocsinh";
//    public static final String FANOUT_EXCHANGE = "exchange.fanout";
//    public static final String TOPIC_EXCHANGE = "exchange.topic";
//    public static final String HEADER_EXCHANGE = "exchange.header";
    // Dùng chung các hàm convert message và rabbit template


}