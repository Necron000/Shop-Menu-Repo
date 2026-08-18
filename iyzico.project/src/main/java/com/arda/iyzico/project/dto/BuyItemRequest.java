package com.arda.iyzico.project.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BuyItemRequest {

    @NotNull
    private Long itemId;

    @NotNull
    @Email
    private String buyerEmail;

    @NotNull
    @Min(1)
    private Integer quantity;
}
