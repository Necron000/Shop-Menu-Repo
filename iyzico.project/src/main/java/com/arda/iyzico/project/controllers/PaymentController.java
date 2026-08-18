package com.arda.iyzico.project.controllers;

import com.arda.iyzico.project.dto.PaymentResult;
import com.arda.iyzico.project.dto.PurchaseOrderResponse;
import com.arda.iyzico.project.services.PaymentCallbackService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentCallbackService paymentCallbackService;

    @PostMapping("/payments/callback")
    public ResponseEntity<PurchaseOrderResponse> handleCallback(@Valid @RequestBody PaymentResult paymentResult) {
        return ResponseEntity.ok(PurchaseOrderResponse.from(paymentCallbackService.handleCallback(paymentResult)));
    }
}
