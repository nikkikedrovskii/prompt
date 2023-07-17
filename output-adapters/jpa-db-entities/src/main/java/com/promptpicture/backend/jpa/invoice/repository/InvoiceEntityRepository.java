package com.promptpicture.backend.jpa.invoice.repository;

import com.promptpicture.backend.jpa.invoice.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceEntityRepository extends JpaRepository<InvoiceEntity, Long> {
}
