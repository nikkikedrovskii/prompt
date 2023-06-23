package com.promptpicture.backend.jpa.prompt.entity;

import com.promptpicture.backend.jpa.customer.entity.CustomerEntity;
import com.promptpicture.backend.jpa.prompt.common.entity.GeneralEntity;
import com.promptpicture.backend.jpa.tag.entity.TagEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.util.List;

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

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private CustomerEntity customerEntity;

    @Column(name = "saved")
    private boolean saved;

    @Column(name = "description")
    @ColumnDefault("description of prompt")
    private String description;

    @Column(name = "price")
    @ColumnDefault("15.0")
    private BigDecimal price;

    @Column(name = "resolution",columnDefinition = "varchar(10) default '512x512'")
    private String resolution;

    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "prompt_tag",
            joinColumns = {@JoinColumn(name = "prompt_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    private List<TagEntity> promptTags;


}
