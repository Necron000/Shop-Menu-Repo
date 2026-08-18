package com.arda.iyzico.project.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    public static final String IYZICO_CHECKOUT_QUEUE = "iyzico.checkout.queue";

    @Bean
    public Queue iyzicoCheckoutQueue() {
        return new Queue(IYZICO_CHECKOUT_QUEUE, true);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
