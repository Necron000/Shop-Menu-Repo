package com.arda.iyzico.project.services;

import com.arda.iyzico.project.dto.PaymentResult;
import com.arda.iyzico.project.models.PurchaseOrder;
import com.arda.iyzico.project.repositories.PurchaseOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentCallbackService {

    private final PurchaseOrderRepository purchaseOrderRepository;

    @Transactional
    public PurchaseOrder handleCallback(PaymentResult paymentResult) {
        PurchaseOrder order = purchaseOrderRepository.findById(paymentResult.getOrderId())
            .orElseThrow(() -> new IllegalArgumentException("Purchase order not found."));

        if (Boolean.TRUE.equals(paymentResult.isApproved())) {
            order.markApproved();
        } else {
            order.markRejected();
        }

        return purchaseOrderRepository.save(order);
    }
}
