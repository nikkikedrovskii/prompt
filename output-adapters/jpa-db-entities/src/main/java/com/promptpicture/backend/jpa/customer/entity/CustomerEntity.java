package com.promptpicture.backend.jpa.customer.entity;

import com.promptpicture.backend.jpa.prompt.common.entity.GeneralEntity;
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
import org.hibernate.annotations.ColumnDefault;

import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "customer")
@SequenceGenerator(name = "prompt_id_seq_gen", sequenceName = "prompt_id_seq", allocationSize = 1)
public class CustomerEntity extends GeneralEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prompt_id_seq_gen")
    @Column(name = "id")
    private Long id;

    @Column(name = "external_customer_id")
    private UUID externalCustomerId;

    @Column(name = "country")
    @ColumnDefault("CZ")
    private String country;

}
