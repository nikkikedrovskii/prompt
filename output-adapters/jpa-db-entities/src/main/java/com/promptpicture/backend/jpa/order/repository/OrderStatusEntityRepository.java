package com.promptpicture.backend.jpa.order.repository;

import com.promptpicture.backend.jpa.order.entity.OrderStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderStatusEntityRepository extends JpaRepository<OrderStatusEntity, Long> {

    Optional<OrderStatusEntity> findOrderStatusEntityByStatus(String status);
}
