package com.promptpicture.backend.jpa.cart.entity;

import com.promptpicture.backend.jpa.prompt.entity.PromptEntity;
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
@Table(name = "cart_item")
@SequenceGenerator(name = "cart_item_id_seq_gen", sequenceName = "cart_item_id_seq", allocationSize = 1)
public class CartItemEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_item_id_seq_gen")
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "prompt_id", referencedColumnName = "id")
    private PromptEntity promptEntity;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private CartEntity cartEntity;

    @Column(name = "description")
    private String description;

}
