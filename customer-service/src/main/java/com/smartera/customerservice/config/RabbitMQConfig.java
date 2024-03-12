package com.smartera.customerservice.config;

import com.smartera.customerservice.messageQueue.MessageReceiver;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yilmazsahin
 * @since 3/8/2024
 */
@Configuration
public class RabbitMQConfig {
    @Bean
    public SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory, MessageReceiver messageReceiver) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames("notificationQueue");
        container.setMessageListener((MessageListener) messageReceiver);
        return container;

    }
}
