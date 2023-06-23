package com.promptpicture.backend.core.vat.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Vat {

    private BigDecimal vatRate;
    private String countryCode;

}
