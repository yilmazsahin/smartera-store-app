package com.smartera.orderservice.messageQueue;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yilmazsahin
 * @since 3/8/2024
 */
@Component
public class MessageSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend("exchange", "routingKey", message);
        System.out.println("Message sent: " + message);
    }
}
