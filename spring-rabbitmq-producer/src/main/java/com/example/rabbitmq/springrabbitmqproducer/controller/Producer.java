package com.example.rabbitmq.springrabbitmqproducer.controller;


import com.example.rabbitmq.springrabbitmqproducer.model.Message;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.example.rabbitmq.springrabbitmqproducer.configuration.RabbitMQConfiguration.*;

@RestController
public class Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/post_email")
    public String send_email(@RequestBody Message message){
        rabbitTemplate.convertAndSend(EMAIL_EXCHANGE,ROUTING_A,message);
        rabbitTemplate.convertAndSend(EMAIL_EXCHANGE,ROUTING_C,message);
        rabbitTemplate.convertAndSend(EMAIL_EXCHANGE,ROUTING_B,message);
        // Ở đây dù đã gửi message tới B nhưng B không nhận được vì không đăng kí vào exchange này, mỗi queue chỉ được nằm trong 1 exchange, thông qua binding
        return "Message sent successfully";
    }
//    @PostMapping("/post_hocsinh")
//    public String send_hocsinh(@RequestBody Message message){
//        rabbitTemplate.convertAndSend(exchange_hocsinh.getName(),"routing.B",message);
////        rabbitTemplate.convertAndSend(exchange_direct.getName(),"routing.B",message);
//        return "Message sent successfully";
//    }

//    @PostMapping("/post_fanout")
//    public String send_fanout(@RequestBody Message message){
//        rabbitTemplate.convertAndSend(exchange_fanout.getName(),"",message);
//        return "Message sent successfully";
//    }
//
//    @PostMapping("/post_topic")
//    public String send_topic(@RequestBody Message message) {
//        rabbitTemplate.convertAndSend(exchange_topic.getName(), "routing.A", message);
////        rabbitTemplate.convertAndSend(exchange_topic.getName(), "routing.B", message);
//        return "Message sent successfully";
//    }
//    @PostMapping("/post_header/{message}")
//    public String send_header(@PathVariable(value="message") String message) {
//        MessageProperties messageProperties = new MessageProperties();
//        messageProperties.setHeader("color",message);
//        MessageConverter messageConverter = new SimpleMessageConverter();
//        org.springframework.amqp.core.Message message1 = messageConverter.toMessage(message,messageProperties);
//        rabbitTemplate.send(exchange_header.getName(),"",message1);
//        return "Message sent successfully";
//    }
}
