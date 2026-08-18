package com.arda.iyzico.project.services;

import com.arda.iyzico.project.dto.CheckoutMessage;
import com.arda.iyzico.project.dto.IyzicoPaymentRequest;
import com.arda.iyzico.project.dto.PaymentResult;
import com.arda.iyzico.project.models.Item;
import com.arda.iyzico.project.repositories.ItemRepository;
import com.arda.iyzico.project.repositories.PurchaseOrderRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class IyzicoCheckoutJob {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final ItemRepository itemRepository;
    private final PaymentCallbackService paymentCallbackService;

    @RabbitListener(queues = "iyzico.checkout.queue")
    @Transactional
    public void process(CheckoutMessage message) {
        Optional<Item> itemOpt = itemRepository.findById(message.getItemId());
        if (itemOpt.isEmpty()) {
            return;
        }

        Item item = itemOpt.get();
        IyzicoPaymentRequest paymentRequest = IyzicoPaymentRequest.builder()
            .orderId(message.getOrderId())
            .itemId(item.getId())
            .itemName(item.getName())
            .buyerEmail(message.getBuyerEmail())
            .quantity(message.getQuantity())
            .amount(message.getAmount())
            .currency(message.getCurrency())
            .build();

        PaymentResult paymentResult = PaymentResult.builder()
            .orderId(paymentRequest.getOrderId())
            .approved(true)
            .transactionId("SIM-" + paymentRequest.getOrderId())
            .message("Iyzico simulation approved")
            .build();

        paymentCallbackService.handleCallback(paymentResult);
    }
}
