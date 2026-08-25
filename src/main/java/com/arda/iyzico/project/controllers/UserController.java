package com.arda.iyzico.project.controllers;

import com.arda.iyzico.project.dto.BuyItemRequest;
import com.arda.iyzico.project.dto.PurchaseOrderResponse;
import com.arda.iyzico.project.services.PurchaseFlowService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final PurchaseFlowService purchaseFlowService;

    @PostMapping("/buy")
    public ResponseEntity<PurchaseOrderResponse> buyItem(@Valid @RequestBody BuyItemRequest request) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(PurchaseOrderResponse.from(
                        purchaseFlowService.createCheckoutRequest(
                                request.getItemId(),
                                request.getBuyerEmail(),
                                request.getQuantity()
                        )));
    }

    @GetMapping("/orders")
    public List<PurchaseOrderResponse> getOrders() {
        return purchaseFlowService.getOrders();
    }
}