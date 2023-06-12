package com.promptpicture.backend.core.prompt.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Prompt {

    private Long id;
    private String b64Json;
    private String promptText;
    private List<String> tags;

}
