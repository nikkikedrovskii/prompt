package com.promptpicture.backend.jpa.vat.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "vat")
@SequenceGenerator(name = "vat_id_seq_gen", sequenceName = "vat_id_seq", allocationSize = 1)
public class VatEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vat_id_seq_gen")
    @Column(name = "id")
    private Long id;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "vat_rate")
    private BigDecimal vatRate;
}
