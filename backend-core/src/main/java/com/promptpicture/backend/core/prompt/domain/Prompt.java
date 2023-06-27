package com.promptpicture.backend.core.prompt.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@Builder
public class Prompt {

    private Long id;
    private String b64Json;
    private String promptText;
    private BigDecimal price;
    private BigDecimal priceWithVat;
    private String description;
    private String resolution;
    private List<String> tags;
    private OffsetDateTime createdAt;
    private boolean saved;

}
