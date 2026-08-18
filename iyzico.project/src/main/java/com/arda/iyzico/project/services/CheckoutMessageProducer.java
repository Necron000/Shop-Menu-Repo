package com.arda.iyzico.project.services;

import com.arda.iyzico.project.dto.CheckoutMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckoutMessageProducer {

    private final RabbitTemplate rabbitTemplate;

    public void send(CheckoutMessage message) {
        rabbitTemplate.convertAndSend("iyzico.checkout.queue", message);
    }
}
