package com.promptpicture.backend.entrypoint.rest.model.output;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CartItemOutput {

    private BigDecimal price;
    private String description;
}
