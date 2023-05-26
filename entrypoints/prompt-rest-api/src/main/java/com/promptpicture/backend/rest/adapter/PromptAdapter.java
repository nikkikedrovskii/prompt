package com.promptpicture.backend.rest.adapter;

import com.promptpicture.backend.core.prompt.PromptFacade;
import com.promptpicture.backend.rest.mapper.Prompt2PromptListOutputMapper;
import com.promptpicture.backend.rest.model.input.CreatePromptInput;
import com.promptpicture.backend.rest.model.output.GeneratePictureOutput;
import com.promptpicture.backend.rest.model.output.PromptOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class PromptAdapter {

    private final PromptFacade promptFacade;
    private final Prompt2PromptListOutputMapper prompt2PromptListOutputMapper;

    public GeneratePictureOutput generatePicture(String textPrompt) {

        var b64Json = promptFacade.create(textPrompt);

        return GeneratePictureOutput.builder()
                .promptText(textPrompt)
                .b64Json(b64Json)
                .build();
    }

    public List<PromptOutput> getListOfPrompt() {

        var listOfPrompt = promptFacade.getListOfPromptUseCase();

        return prompt2PromptListOutputMapper.toListOfPromptOutput(listOfPrompt);
    }

    public PromptOutput getPromptDetail(Long id) {
        var prompt = promptFacade.getPromptDetailUseCase(id);
        return prompt2PromptListOutputMapper.toPromptOutput(prompt);
    }

    public void createPrompt(CreatePromptInput createPromptInput) {
        var promptText = createPromptInput.getPromptText();
        var b64Json = createPromptInput.getB64Json();
        promptFacade.createPrompt(promptText,b64Json);
    }
}
