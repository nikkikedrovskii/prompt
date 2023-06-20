package com.promptpicture.backend.core.cart.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CartItem {

    private Long promptId;
    private BigDecimal price;
    private String b64Json;
    private String description;

}
