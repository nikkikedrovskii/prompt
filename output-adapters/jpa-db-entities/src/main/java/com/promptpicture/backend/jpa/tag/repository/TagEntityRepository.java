package com.promptpicture.backend.jpa.tag.repository;

import com.promptpicture.backend.jpa.tag.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagEntityRepository extends JpaRepository<TagEntity, Long> {

    List<TagEntity> findAllByTagNameIn(List<String> tagName);
}
