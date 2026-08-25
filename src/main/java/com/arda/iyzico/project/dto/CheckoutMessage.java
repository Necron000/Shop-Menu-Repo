package com.arda.iyzico.project.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckoutMessage {
    private Long orderId;
    private Long itemId;
    private String buyerEmail;
    private Integer quantity;
    private BigDecimal amount;
    private String currency;
    private String checkoutToken;
}
