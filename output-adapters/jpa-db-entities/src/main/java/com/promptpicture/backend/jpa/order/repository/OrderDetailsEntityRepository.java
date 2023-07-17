package com.promptpicture.backend.jpa.order.repository;

import com.promptpicture.backend.jpa.order.entity.OrderDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsEntityRepository extends JpaRepository<OrderDetailsEntity, Long> {
}
