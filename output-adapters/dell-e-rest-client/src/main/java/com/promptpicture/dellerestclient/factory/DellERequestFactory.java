package com.promptpicture.dellerestclient.factory;

import com.promptpicture.backend.core.prompt.domain.CreateIndividualPrompt;
import com.promptpicture.dellerestclient.domain.DellERequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DellERequestFactory {

    public DellERequest create(String prompt){

        return DellERequest.builder()
                .prompt(prompt)
                .n(1)
                .size("512x512")
                .response_format("b64_json")
                .build();

    }

    public DellERequest create(CreateIndividualPrompt createIndividualPrompt){

        return DellERequest.builder()
                .prompt(createIndividualPrompt.getPromptText())
                .n(1)
                .size(createIndividualPrompt.getResolution())
                .response_format("b64_json")
                .build();

    }
}
