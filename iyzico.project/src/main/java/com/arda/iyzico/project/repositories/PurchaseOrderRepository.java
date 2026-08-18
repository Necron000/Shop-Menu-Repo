package com.arda.iyzico.project.repositories;

import com.arda.iyzico.project.models.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
}
