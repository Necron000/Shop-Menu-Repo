package com.arda.iyzico.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResult {
    private Long orderId;
    private boolean approved;
    private String transactionId;
    private String message;
}
