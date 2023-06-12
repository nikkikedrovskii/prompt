package com.promptpicture.backend.entrypoint.rest.model.output;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PromptOutput {

    private Long id;
    private String b64Json;
    private String promptText;
    private List<String> tags;

}
