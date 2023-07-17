package com.promptpicture.backend.jpa.prompt.repository;

import com.promptpicture.backend.jpa.prompt.entity.PromptOrderDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromptDataEntityRepository extends JpaRepository<PromptOrderDataEntity, Long> {

}
