package com.promptpicture.backend.core.order.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PromptInOrder {

    private Long promptId;
    private String resolution;
    private boolean individual;
    private BigDecimal priceWithoutVat;
    private BigDecimal priceWithVat;
    private String b64Json;
}
