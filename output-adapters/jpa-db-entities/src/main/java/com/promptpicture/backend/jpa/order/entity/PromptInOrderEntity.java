package com.promptpicture.backend.jpa.order.entity;

import com.promptpicture.backend.jpa.prompt.entity.PromptOrderDataEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
@Table(name = "prompt_in_order")
@SequenceGenerator(name = "prompt_in_order_id_seq_gen", sequenceName = "prompt_in_order_id_seq", allocationSize = 1)
public class PromptInOrderEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prompt_in_order_id_seq_gen")
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_details_id")
    private OrderDetailsEntity orderDetailsEntity;

    @Column(name = "prompt_id")
    private Long promptId;

    @Column(name = "resolution")
    private String resolution;

    @Column(name = "price_without_vat")
    private BigDecimal priceWithoutVat;

    @Column(name = "individual")
    private Boolean individual;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "prompt_data_id")
    private PromptOrderDataEntity promptOrderDataEntity;

}
