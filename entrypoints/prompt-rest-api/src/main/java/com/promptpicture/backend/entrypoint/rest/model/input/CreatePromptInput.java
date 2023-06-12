package com.promptpicture.backend.entrypoint.rest.model.input;

import lombok.Data;

import java.util.List;

@Data
public class CreatePromptInput {

    private Long promptId;
    private List<String> listOfTags;

}
