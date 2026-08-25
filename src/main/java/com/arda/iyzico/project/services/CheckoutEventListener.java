package com.arda.iyzico.project.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class CheckoutEventListener {

    private final CheckoutMessageProducer checkoutMessageProducer;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onCheckoutRequested(CheckoutRequestedEvent event) {
        checkoutMessageProducer.send(event.message());
    }
}