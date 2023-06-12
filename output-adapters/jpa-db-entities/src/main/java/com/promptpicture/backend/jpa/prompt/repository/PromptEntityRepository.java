package com.promptpicture.backend.jpa.prompt.repository;

import com.promptpicture.backend.jpa.prompt.entity.PromptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.UUID;

public interface PromptEntityRepository extends JpaRepository<PromptEntity,Long>, JpaSpecificationExecutor<PromptEntity> {

    List<PromptEntity> getPromptEntityByUserIdAndSavedIsFalseOrderByCreatedAtAsc(UUID userId);

    List<PromptEntity> getPromptEntityBySavedIsTrue();

}
