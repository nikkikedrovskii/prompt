package com.promptpicture.backend.entrypoint.rest.model.output;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class CartOutput {

    private UUID externalCustomerId;
    private BigDecimal totalPrice;
    private List<CartItemOutput> cartItemOutputList;

}
