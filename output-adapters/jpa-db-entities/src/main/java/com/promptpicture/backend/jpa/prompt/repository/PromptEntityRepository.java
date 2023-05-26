package com.promptpicture.backend.jpa.prompt.repository;

import com.promptpicture.backend.jpa.prompt.entity.PromptEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromptEntityRepository extends JpaRepository<PromptEntity,Long> {
}
