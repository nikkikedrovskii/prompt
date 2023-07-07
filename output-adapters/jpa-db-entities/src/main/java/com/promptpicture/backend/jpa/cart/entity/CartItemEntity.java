package com.promptpicture.backend.jpa.cart.entity;

import com.promptpicture.backend.jpa.price.entity.PriceEntity;
import com.promptpicture.backend.jpa.prompt.entity.PromptEntity;
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

    @OneToOne
    @JoinColumn(name = "prompt_id", referencedColumnName = "id")
    private PromptEntity promptEntity;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private CartEntity cartEntity;

    @OneToOne
    @JoinColumn(name = "price_id", referencedColumnName = "id")
    private PriceEntity priceEntity;

}
