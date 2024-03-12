package com.smartera.customerservice.messageQueue;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yilmazsahin
 * @since 3/8/2024
 */

@Component
public class MessageReceiver {

    @RabbitListener(queues = "notificationQueue")
    public void receiveMessage(String message) {
        System.out.println("Message received" + message);
    }
}
