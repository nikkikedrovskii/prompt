package com.promptpicture.backend.entrypoint.rest.model.input;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateIndividualPromptRequest {

    private UUID userId;
    private String resolution;
    private String promptText;

}
