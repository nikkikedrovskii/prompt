package com.promptpicture.backend.jpa.cart.repository;

import com.promptpicture.backend.jpa.cart.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartItemEntityRepository extends JpaRepository<CartItemEntity, Long> {

    @Query("SELECT cie FROM CartItemEntity cie JOIN FETCH cie.promptEntity" +
            " WHERE cie.promptEntity.id = :promptId ")
    Optional<CartItemEntity> findCartItemEntityByPromptId(@Param("promptId")Long promptId);
}
