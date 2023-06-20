package com.promptpicture.backend.jpa.cart.repository;

import com.promptpicture.backend.jpa.cart.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CartEntityRepository extends JpaRepository<CartEntity, Long> {

    Optional<CartEntity> findByExternalCustomerId(UUID externalCustomerId);

}
