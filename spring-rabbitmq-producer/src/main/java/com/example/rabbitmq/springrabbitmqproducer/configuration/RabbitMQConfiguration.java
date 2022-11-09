package com.example.rabbitmq.springrabbitmqproducer.configuration;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    // Khai báo các object
    public static final String QUEUE_A = "queue.A";
    public static final String QUEUE_B = "queue.B";
    public static final String ALL_QUEUE = "queue.all";
    public static final String ROUTING_A = "routing.A";
    public static final String ROUTING_B = "routing.B";
    public static final String ALL_ROUTING = "routing.*";

    public static final String DIRECT_EXCHANGE = "exchange.direct";
    public static final String FANOUT_EXCHANGE = "exchange.fanout";
    public static final String TOPIC_EXCHANGE = "exchange.topic";
    public static final String HEADER_EXCHANGE = "exchange.header";


    @Bean
    Queue queueA() {
        return new Queue(QUEUE_A, false);
    }

    @Bean
    Queue queueB() {
        return new Queue(QUEUE_B, false);
    }
    @Bean
    Queue allQueue() {
        return new Queue(ALL_QUEUE, false);
    }

//        @Bean
//    DirectExchange exchange_direct(){
//        return new DirectExchange(DIRECT_EXCHANGE);
//    }
//
//    @Bean
//    FanoutExchange exchange_fanout(){
//        return new FanoutExchange(FANOUT_EXCHANGE);
//    }
//    @Bean
//    TopicExchange exchange_topic() {
//        return new TopicExchange(TOPIC_EXCHANGE);
//    }
        @Bean
        HeadersExchange exchange_header() {
        return new HeadersExchange(HEADER_EXCHANGE);
    }


////      DirectExchange binding
//
//    @Bean
//    Binding bindingA(Queue queueA,DirectExchange exchange_direct){
//        return BindingBuilder.bind(queueA).to(exchange_direct).with(ROUTING_A);
//    }
//    @Bean
//    Binding bindingB(Queue queueB,DirectExchange exchange_direct){
//        return BindingBuilder.bind(queueB).to(exchange_direct).with(ROUTING_B);
//    }


////     FanoutExchange binding
//    @Bean
//    Binding bindingA(Queue queueA,FanoutExchange exchange_fanout){
//        return BindingBuilder.bind(queueA).to(exchange_fanout);
//    }
//    @Bean
//    Binding bindingB(Queue queueB,FanoutExchange exchange_fanout){
//        return BindingBuilder.bind(queueB).to(exchange_fanout);
//    }


//     TopicExchange binding
//    @Bean
//    Binding bindingA(Queue queueA, TopicExchange exchange_topic) {
//        return BindingBuilder.bind(queueA).to(exchange_topic).with(ROUTING_A);
//    }
//
//    @Bean
//    Binding bindingB(Queue queueB, TopicExchange exchange_topic) {
//        return BindingBuilder.bind(queueB).to(exchange_topic).with(ROUTING_B);
//    }
//    @Bean
//    Binding bindingAll(Queue allQueue, TopicExchange exchange_topic) {
//        return BindingBuilder.bind(allQueue).to(exchange_topic).with(ALL_ROUTING);
//    }

    //     HeadersExchange binding
        @Bean
    Binding bindingA(Queue queueA, HeadersExchange exchange_header) {
        return BindingBuilder.bind(queueA).to(exchange_header).where("color").matches("red");
    }

    @Bean
    Binding bindingB(Queue queueB, HeadersExchange exchange_header) {
        return BindingBuilder.bind(queueB).to(exchange_header).where("color").matches("blue");
    }
    @Bean
    Binding bindingAll(Queue allQueue, HeadersExchange exchange_header) {
        return BindingBuilder.bind(allQueue).to(exchange_header).where("color").matches("green");
    }


    // Dùng chung các hàm convert message và rabbit template
    @Bean
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
