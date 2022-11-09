package com.example.rabbitmq.springrabbitmqconsumer.configuration;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {


    public static final String QUEUE_A = "queue.A";
    public static final String QUEUE_B = "queue.B";
    public static final String ROUTING_A = "routing.A";
    public static final String ROUTING_B = "routing.B";
    public static final String DIRECT_EXCHANGE = "exchange.direct";


    @Bean
    Queue queueA(){
        return new Queue(QUEUE_A, false);
    }
    @Bean
    Queue queueB(){
        return new Queue(QUEUE_B,false);
    }

    @Bean
    DirectExchange exchange(){
        return new DirectExchange(DIRECT_EXCHANGE);
    }

    @Bean
    Binding bindingA(Queue queueA,DirectExchange exchange){
        return BindingBuilder.bind(queueA).to(exchange).with(ROUTING_A);
    }
    @Bean
    Binding bindingB(Queue queueB,DirectExchange exchange){
        return BindingBuilder.bind(queueB).to(exchange).with(ROUTING_B);
    }

    @Bean
    MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory factory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

}
