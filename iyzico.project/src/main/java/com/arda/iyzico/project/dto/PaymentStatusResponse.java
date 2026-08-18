package com.arda.iyzico.project.dto;

import com.arda.iyzico.project.models.PaymentStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaymentStatusResponse {
    private final Long orderId;
    private final PaymentStatus status;
}
