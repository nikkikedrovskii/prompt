package com.promptpicture.backend.core.prompt.domain;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CreateIndividualPrompt {

    private UUID externalCustomerId;
    private String resolution;
    private String promptText;

}
