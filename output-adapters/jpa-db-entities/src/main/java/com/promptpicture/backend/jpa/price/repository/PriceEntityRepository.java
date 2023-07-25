package com.promptpicture.backend.jpa.price.repository;

import com.promptpicture.backend.jpa.price.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PriceEntityRepository extends JpaRepository<PriceEntity, Long> {

    Optional<PriceEntity> findPriceEntityByResolution(String resolution);
}
