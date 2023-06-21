package com.promptpicture.backend.core.cart.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class Cart {

    private UUID externalCustomerId;
    private BigDecimal totalPrice;
    private BigDecimal totalPriceWithVat;
    private BigDecimal vatRate;
    private List<CartItem> cartItemOutputList;
}
