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

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "prompt_picture")
@SequenceGenerator(name = "prompt_picture_id_seq_gen", sequenceName = "prompt_picture_id_seq", allocationSize = 1)
public class PromptPictureEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prompt_picture_id_seq_gen")
    @Column(name = "id")
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "file_b64_json_text")
    private String fileB64JsonText;
}
