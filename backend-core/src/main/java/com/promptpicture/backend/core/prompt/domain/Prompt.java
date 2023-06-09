package com.promptpicture.backend.core.prompt.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Prompt {

    private Long id;
    private String b64Json;
    private String promptText;

}
