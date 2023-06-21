package com.promptpicture.backend.jpa.vat.repository;

import com.promptpicture.backend.jpa.vat.entity.VatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VatEntityRepository extends JpaRepository<VatEntity, Long> {

    Optional<VatEntity> findVatEntityByCountryCode(String countryCode);
}
