package com.promptpicture.backend.jpa.order.repository;

import com.promptpicture.backend.jpa.order.entity.PromptInOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PromptInOrderEntityRepository extends JpaRepository<PromptInOrderEntity, Long> {

    @Query("SELECT pio FROM PromptInOrderEntity pio" +
            " WHERE pio.promptId = :promptId " +
            "AND pio.resolution = :resolution")
    Optional<PromptInOrderEntity> getPromptInOrderEntitiesByPromptIdAndResolution(@Param("promptId") Long promptId, @Param("resolution") String resolution);
}
