package com.promptpicture.backend.entrypoint.rest.model.output;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class PromptResponse {

    private Long id;
    private String b64Json;
    private String promptText;
    private BigDecimal price;
    private BigDecimal priceWithVat;
    private String description;
    private String resolution;
    private List<String> tags;

}
