package com.promptpicture.backend.jpa.order.repository;

import com.promptpicture.backend.jpa.order.entity.OrderDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrderDetailsEntityRepository extends JpaRepository<OrderDetailsEntity, Long> {

    Optional<OrderDetailsEntity> findOrderDetailsEntityByExternalCustomerId(UUID externalCustomerId);

}
