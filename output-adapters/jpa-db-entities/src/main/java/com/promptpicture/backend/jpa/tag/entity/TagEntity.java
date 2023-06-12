package com.promptpicture.backend.jpa.tag.entity;

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
@Table(name = "tag")
@SequenceGenerator(name = "tag_id_seq_gen", sequenceName = "tag_id_seq", allocationSize = 1)
public class TagEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tag_id_seq_gen")
    @Column(name = "id")
    private Long id;

    @Column(name = "tag_name")
    private String tagName;
}
