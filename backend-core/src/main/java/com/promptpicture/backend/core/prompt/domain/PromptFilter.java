package com.promptpicture.backend.core.prompt.domain;

import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class PromptFilter {

    private List<String> listOfTag = Collections.emptyList();
}
