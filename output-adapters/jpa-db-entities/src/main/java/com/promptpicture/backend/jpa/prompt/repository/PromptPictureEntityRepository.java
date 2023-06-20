package com.promptpicture.backend.jpa.prompt.repository;

import com.promptpicture.backend.jpa.prompt.entity.PromptPictureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromptPictureEntityRepository extends JpaRepository<PromptPictureEntity, Long> {


}
