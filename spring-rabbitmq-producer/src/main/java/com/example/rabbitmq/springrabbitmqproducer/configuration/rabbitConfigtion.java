package com.example.rabbitmq.springrabbitmqproducer.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import static com.example.rabbitmq.springrabbitmqproducer.configuration.RabbitMQConfiguration.*;

@Configuration
public class rabbitConfigtion {

    public static Map< String,Object> args ; // Thời gian tin nhắn được lưu trên queue tính bằng mili giây,86400000 mili giây là 24h
    static {
        args= new HashMap<>();
        args.put("x-message-ttl", 86400000); // x-message: time-to-live = 24h
    }

    @Bean
    Queue queueA() {
        return new Queue(QUEUE_A, false,true,false,args);
    }

    @Bean
    Queue queueB() {
        return new Queue(QUEUE_B, false,true,false,args);
    }

    @Bean
    Queue queueC() {
        return new Queue(QUEUE_C, false,true,false,args);
    }

    @Bean
    TopicExchange exchange_email() {
        return new TopicExchange(EMAIL_EXCHANGE);
    }

    @Bean
    DirectExchange exchange_hocsinh() {
        return new DirectExchange(HOC_SINH_EXCHANGE);
    }

    //      Exchange binding
    @Bean
    Binding bindingA(Queue queueA, TopicExchange exchange_email) {
        return BindingBuilder.bind(queueA).to(exchange_email).with(ROUTING_A);
    }

    @Bean
    Binding bindingB(Queue queueB, DirectExchange exchange_hocsinh) {
        return BindingBuilder.bind(queueB).to(exchange_hocsinh).with(ROUTING_B);
    }

    @Bean
    Binding bindingC(Queue queueC, TopicExchange exchange_email) {
        return BindingBuilder.bind(queueC).to(exchange_email).with(ROUTING_C);
    }
    MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory factory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

}
