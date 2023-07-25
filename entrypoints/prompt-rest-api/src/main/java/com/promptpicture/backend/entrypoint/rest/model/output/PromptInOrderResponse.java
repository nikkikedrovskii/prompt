package com.promptpicture.backend.entrypoint.rest.model.output;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PromptInOrderResponse {

    private BigDecimal priceWithoutVat;
    private BigDecimal priceWithVat;
    private String resolution;
    private String b64Json;

}
