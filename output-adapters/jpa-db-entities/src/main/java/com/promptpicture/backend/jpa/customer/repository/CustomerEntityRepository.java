package com.promptpicture.backend.jpa.customer.repository;

import com.promptpicture.backend.jpa.customer.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerEntityRepository extends JpaRepository<CustomerEntity, Long> {

    Optional<CustomerEntity> findByExternalCustomerId(UUID externalCustomerId);
}
