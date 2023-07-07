package com.promptpicture.backend.jpa.payment_method.repository;

import com.promptpicture.backend.jpa.payment_method.entity.PaymentMethodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentMethodEntityRepository extends JpaRepository<PaymentMethodEntity, Long> {

    Optional<PaymentMethodEntity> findPaymentMethodEntitiesByPaymentMethodName(String paymentMethod);
}
