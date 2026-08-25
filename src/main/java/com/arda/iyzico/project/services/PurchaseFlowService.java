package com.arda.iyzico.project.services;

import com.arda.iyzico.project.dto.PurchaseOrderResponse;
import com.arda.iyzico.project.models.PurchaseOrder;
import java.util.List;

public interface PurchaseFlowService {
    PurchaseOrder createCheckoutRequest(Long itemId, String buyerEmail, int quantity);
    List<PurchaseOrderResponse> getOrders();
}
