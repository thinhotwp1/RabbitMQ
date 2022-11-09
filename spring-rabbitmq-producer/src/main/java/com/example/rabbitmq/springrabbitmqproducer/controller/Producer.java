package com.example.rabbitmq.springrabbitmqproducer.controller;


import com.example.rabbitmq.springrabbitmqproducer.model.Message;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

//    @Autowired
//    private DirectExchange exchange_direct;

//    @Autowired
//    private FanoutExchange exchange_fanout;

//    @Autowired
//    private TopicExchange exchange_topic;

    @Autowired
    private HeadersExchange exchange_header;

//    @PostMapping("/post_direct")
//    public String send(@RequestBody Message message){
//        rabbitTemplate.convertAndSend(exchange_direct.getName(),"routing.A",message);
//        rabbitTemplate.convertAndSend(exchange_direct.getName(),"routing.B",message);
//        return "Message sent successfully";
//    }

//    @PostMapping("/post_fanout")
//    public String send(@RequestBody Message message){
//        rabbitTemplate.convertAndSend(exchange_fanout.getName(),"",message);
//        return "Message sent successfully";
//    }

//    @PostMapping("/post_topic")
//    public String send(@RequestBody Message message) {
//        rabbitTemplate.convertAndSend(exchange_topic.getName(), "routing.A", message);
////        rabbitTemplate.convertAndSend(exchange_topic.getName(), "routing.B", message);
//        return "Message sent successfully";
//    }
    @PostMapping("/post_header/{message}")
    public String send(@PathVariable(value="message") String message) {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("color",message);
        MessageConverter messageConverter = new SimpleMessageConverter();
        org.springframework.amqp.core.Message message1 = messageConverter.toMessage(message,messageProperties);
        rabbitTemplate.send(exchange_header.getName(),"",message1);
        return "Message sent successfully";
    }
}
