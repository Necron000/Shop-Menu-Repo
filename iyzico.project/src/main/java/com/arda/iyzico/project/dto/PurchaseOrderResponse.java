package com.arda.iyzico.project.dto;

import com.arda.iyzico.project.models.PaymentStatus;
import com.arda.iyzico.project.models.PurchaseOrder;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PurchaseOrderResponse {
    private final Long id;
    private final Long itemId;
    private final String buyerEmail;
    private final Integer quantity;
    private final BigDecimal amount;
    private final String currency;
    private final PaymentStatus status;
    private final String checkoutToken;
    private final Instant createdAt;

    public static PurchaseOrderResponse from(PurchaseOrder purchaseOrder) {
        return PurchaseOrderResponse.builder()
            .id(purchaseOrder.getId())
            .itemId(purchaseOrder.getItemId())
            .buyerEmail(purchaseOrder.getBuyerEmail())
            .quantity(purchaseOrder.getQuantity())
            .amount(purchaseOrder.getAmount())
            .currency(purchaseOrder.getCurrency())
            .status(purchaseOrder.getStatus())
            .checkoutToken(purchaseOrder.getCheckoutToken())
            .createdAt(purchaseOrder.getCreatedAt())
            .build();
    }
}
