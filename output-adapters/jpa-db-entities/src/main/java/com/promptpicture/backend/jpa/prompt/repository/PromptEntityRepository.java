package com.promptpicture.backend.jpa.prompt.repository;

import com.promptpicture.backend.jpa.prompt.entity.PromptEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PromptEntityRepository extends JpaRepository<PromptEntity,Long> {

    List<PromptEntity> getPromptEntityByUserIdAndSavedIsFalseOrderByCreatedAtAsc(UUID userId);

    List<PromptEntity> getPromptEntityBySavedIsTrue();

}
