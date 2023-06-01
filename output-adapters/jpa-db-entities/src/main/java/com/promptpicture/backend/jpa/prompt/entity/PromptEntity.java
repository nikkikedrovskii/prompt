package com.promptpicture.backend.jpa.prompt.entity;

import com.promptpicture.backend.jpa.prompt.common.entity.GeneralEntity;
import jakarta.persistence.CascadeType;
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

import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "prompt")
@SequenceGenerator(name = "prompt_id_seq_gen", sequenceName = "prompt_id_seq", allocationSize = 1)
public class PromptEntity extends GeneralEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prompt_id_seq_gen")
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "picture_id", referencedColumnName = "id")
    private PromptPictureEntity promptPictureEntity;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "saved")
    private boolean saved;


}
