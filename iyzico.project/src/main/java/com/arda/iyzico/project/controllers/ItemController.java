package com.arda.iyzico.project.controllers;

import com.arda.iyzico.project.dto.BuyItemRequest;
import com.arda.iyzico.project.dto.CreateItemRequest;
import com.arda.iyzico.project.dto.ItemResponse;
import com.arda.iyzico.project.dto.PurchaseOrderResponse;
import com.arda.iyzico.project.services.ItemService;
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
@RequestMapping("/api")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final PurchaseFlowService purchaseFlowService;

    @GetMapping("/items")
    public List<ItemResponse> getItems() {
        return itemService.getActiveItems();
    }

    @PostMapping("/admin/items")
    public ResponseEntity<ItemResponse> createItem(@Valid @RequestBody CreateItemRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.createItem(request));
    }

    @PostMapping("/users/buy")
    public ResponseEntity<PurchaseOrderResponse> buyItem(@Valid @RequestBody BuyItemRequest request) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
            .body(PurchaseOrderResponse.from(
                purchaseFlowService.createCheckoutRequest(
                    request.getItemId(),
                    request.getBuyerEmail(),
                    request.getQuantity()
                )));
    }
}
