package com.promptpicture.backend.core.price.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Price {

    private String resolution;
    private BigDecimal defaultPrice;
    private BigDecimal individualPrice;

}
