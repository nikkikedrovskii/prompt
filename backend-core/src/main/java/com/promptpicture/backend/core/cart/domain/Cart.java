package com.promptpicture.backend.core.cart.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class Cart {

    private Long id;
    private UUID externalCustomerId;
    private BigDecimal totalPrice;
    private BigDecimal totalPriceWithVat;
    private BigDecimal vatRate;
    private List<CartItem> cartItemOutputList;
    private OffsetDateTime updateAt;
}
