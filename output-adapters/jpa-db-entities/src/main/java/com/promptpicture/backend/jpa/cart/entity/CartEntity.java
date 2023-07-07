package com.promptpicture.backend.jpa.cart.entity;

import com.promptpicture.backend.jpa.payment_method.entity.PaymentMethodEntity;
import com.promptpicture.backend.jpa.prompt.common.entity.GeneralEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "cart")
@SequenceGenerator(name = "prompt_id_seq_gen", sequenceName = "prompt_id_seq", allocationSize = 1)
public class CartEntity extends GeneralEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prompt_id_seq_gen")
    @Column(name = "id")
    private Long id;

    @Column(name = "external_customer_id")
    private UUID externalCustomerId;

    @Column(name = "price_without_vat")
    private BigDecimal priceWithoutVat;

    @Column(name = "price_with_vat")
    private BigDecimal priceWithVat;

    @Column(name = "vat")
    private BigDecimal vatValue;

    @Column(name = "name")
    private BigDecimal name;

    @Column(name = "last_name")
    private BigDecimal lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "country")
    private String country;

    @Column(name = "dialcode")
    private String dialcode;

    @Column(name = "phone")
    private String phone;

    @Column(name = "invoice_email")
    private String invoiceEmail;

    @Column(name = "email")
    private String email;

    @OneToOne
    @JoinColumn(name = "payment_method_id")
    private PaymentMethodEntity paymentMethodEntity;

    @Column(name = "description")
    private String description;

    @Column(name = "consent_data")
    private OffsetDateTime consentData;

    @OneToMany(mappedBy = "cartEntity", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<CartItemEntity> cartItemEntities;

}
