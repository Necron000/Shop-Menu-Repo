package com.arda.iyzico.project.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IyzicoPaymentRequest {

    @NotNull
    private Long orderId;

    @NotNull
    private Long itemId;

    @NotBlank
    private String itemName;

    @NotBlank
    @Email
    private String buyerEmail;

    @NotNull
    @Min(1)
    private Integer quantity;

    @NotNull
    private BigDecimal amount;

    @NotBlank
    @jakarta.validation.constraints.Size(min = 3, max = 3)
    private String currency;
}
