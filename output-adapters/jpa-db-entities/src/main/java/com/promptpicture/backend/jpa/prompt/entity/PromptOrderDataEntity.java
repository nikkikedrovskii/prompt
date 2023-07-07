package com.promptpicture.backend.jpa.prompt.entity;

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
import java.time.OffsetDateTime;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "prompt_order_data")
@SequenceGenerator(name = "prompt_order_data_id_seq_gen", sequenceName = "prompt_order_data_id_seq", allocationSize = 1)
public class PromptOrderDataEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prompt_order_data_id_seq_gen")
    @Column(name = "id")
    private Long id;

    @Column(name = "prompt_id")
    private Long promptId;

    @Column(name = "prompt_data")
    private String promptData;

    @Column(name = "prompt_text")
    private String promptText;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;
}
