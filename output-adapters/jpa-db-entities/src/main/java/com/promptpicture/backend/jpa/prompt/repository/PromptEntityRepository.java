package com.promptpicture.backend.jpa.prompt.repository;

import com.promptpicture.backend.jpa.prompt.entity.PromptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PromptEntityRepository extends JpaRepository<PromptEntity,Long>, JpaSpecificationExecutor<PromptEntity> {

    @Query("SELECT pe FROM PromptEntity pe JOIN FETCH pe.customerEntity" +
            " WHERE pe.customerEntity.externalCustomerId = :externalCustomerId " +
            "AND pe.saved = false " +
            "AND pe.individual = false " +
            " ORDER BY pe.id ASC ")
    List<PromptEntity> getPromptEntityByUserIdAndSavedIsFalseOrderByCreatedAtAsc(@Param("externalCustomerId") UUID externalCustomerId);

    List<PromptEntity> getPromptEntityBySavedIsTrueAndIndividualIsFalse();

}
