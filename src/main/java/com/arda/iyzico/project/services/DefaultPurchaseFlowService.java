package com.arda.iyzico.project.services;

import com.arda.iyzico.project.dto.CheckoutMessage;
import com.arda.iyzico.project.dto.PurchaseOrderResponse;
import com.arda.iyzico.project.models.Item;
import com.arda.iyzico.project.models.PaymentStatus;
import com.arda.iyzico.project.models.PurchaseOrder;
import com.arda.iyzico.project.repositories.PurchaseOrderRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DefaultPurchaseFlowService implements PurchaseFlowService {

    private final ItemService itemService;
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional
    public PurchaseOrder createCheckoutRequest(Long itemId, String buyerEmail, int quantity) {
        Item item = itemService.getItemForPurchase(itemId);
        item.sell(quantity);

        BigDecimal amount = item.getPrice().multiply(BigDecimal.valueOf(quantity));

        PurchaseOrder purchaseOrder = PurchaseOrder.builder()
                .itemId(item.getId())
                .buyerEmail(buyerEmail)
                .quantity(quantity)
                .amount(amount)
                .currency(item.getCurrency())
                .status(PaymentStatus.PENDING)
                .build();

        PurchaseOrder saved = purchaseOrderRepository.save(purchaseOrder);

        String checkoutToken = UUID.randomUUID().toString();
        saved.markQueued(checkoutToken);

        CheckoutMessage message = CheckoutMessage.builder()
                .orderId(saved.getId())
                .itemId(item.getId())
                .buyerEmail(buyerEmail)
                .quantity(quantity)
                .amount(amount)
                .currency(item.getCurrency())
                .checkoutToken(checkoutToken)
                .build();

        eventPublisher.publishEvent(new CheckoutRequestedEvent(message));

        return saved;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PurchaseOrderResponse> getOrders() {
        return purchaseOrderRepository.findAll().stream()
                .map(PurchaseOrderResponse::from)
                .toList();
    }
}