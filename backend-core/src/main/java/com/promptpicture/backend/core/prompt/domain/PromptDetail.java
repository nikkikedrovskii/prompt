package com.promptpicture.backend.core.prompt.domain;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class PromptDetail {

    private Long id;
    private UUID externalCustomerId;
}
