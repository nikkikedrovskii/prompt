package com.promptpicture.backend.entrypoint.rest.model.output;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateIndividualPromptResponse {

    private Long id;
    private String promptText;
    private String b64Json;
}
