package com.promptpicture.backend.rest.model.output;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PromptOutput {

    private Long id;
    private String b64Json;
    private String promptText;

}
