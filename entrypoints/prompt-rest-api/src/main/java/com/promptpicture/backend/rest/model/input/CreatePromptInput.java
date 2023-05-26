package com.promptpicture.backend.rest.model.input;

import lombok.Data;

@Data
public class CreatePromptInput {

    private String promptText;
    private String b64Json;

}
