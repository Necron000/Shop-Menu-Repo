package com.arda.iyzico.project.controllers;

import com.arda.iyzico.project.dto.PurchaseOrderResponse;
import com.arda.iyzico.project.models.PurchaseOrder;
import com.arda.iyzico.project.repositories.PurchaseOrderRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderController {

    private final PurchaseOrderRepository purchaseOrderRepository;

    @GetMapping("/orders")
    public List<PurchaseOrderResponse> getOrders() {
        return purchaseOrderRepository.findAll().stream()
            .map(PurchaseOrderResponse::from)
            .toList();
    }

    @GetMapping("/orders/{id}")
    public PurchaseOrderResponse getOrder(@PathVariable Long id) {
        PurchaseOrder order = purchaseOrderRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Purchase order not found."));
        return PurchaseOrderResponse.from(order);
    }
}
