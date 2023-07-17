package com.promptpicture.backend.jpa.invoice.entity;

import com.promptpicture.backend.jpa.order.entity.OrderDetailsEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "invoice")
@SequenceGenerator(name = "prompt_id_seq_gen", sequenceName = "prompt_id_seq", allocationSize = 1)
public class InvoiceEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prompt_id_seq_gen")
    @Column(name = "id")
    private Long id;

    @Column(name = "external_invoice_id")
    private UUID externalInvoiceId;

    @OneToOne
    @JoinColumn(name = "order_details_id", referencedColumnName = "id")
    private OrderDetailsEntity orderDetailsEntity;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

}
