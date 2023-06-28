package com.promptpicture.backend.core.cart.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CartItem {

    private Long id;
    private Long promptId;
    private BigDecimal price;
    private BigDecimal priceWithVat;
    private String b64Json;
    private String description;
    private String resolution;

}
