package com.promptpicture.backend.jpa.price.entity;

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
@Table(name = "price")
@SequenceGenerator(name = "price_id_seq_gen", sequenceName = "price_id_seq", allocationSize = 1)
public class PriceEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prompt_id_seq_gen")
    @Column(name = "id")
    private Long id;

    @Column(name = "resolution")
    private String resolution;

    @Column(name = "default_price")
    private BigDecimal defaultPrice;

    @Column(name = "individual_price")
    private BigDecimal individualPrice;

}
