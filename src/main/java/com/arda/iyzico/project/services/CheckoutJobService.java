package com.arda.iyzico.project.services;

import com.arda.iyzico.project.dto.CheckoutMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class CheckoutJobService {

    private final IyzicoCheckoutJob iyzicoCheckoutJob;

    public CheckoutJobService(IyzicoCheckoutJob iyzicoCheckoutJob) {
        this.iyzicoCheckoutJob = iyzicoCheckoutJob;
    }

    @RabbitListener(queues = "iyzico.checkout.queue")
    public void processCheckout(CheckoutMessage message) {
        iyzicoCheckoutJob.process(message);
    }
}
