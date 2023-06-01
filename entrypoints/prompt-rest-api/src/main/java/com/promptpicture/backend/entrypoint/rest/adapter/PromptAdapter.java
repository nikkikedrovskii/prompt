package com.promptpicture.backend.entrypoint.rest.adapter;

import com.promptpicture.backend.core.prompt.PromptFacade;
import com.promptpicture.backend.entrypoint.rest.mapper.Prompt2PromptListOutputMapper;
import com.promptpicture.backend.entrypoint.rest.model.input.CreatePromptInput;
import com.promptpicture.backend.entrypoint.rest.model.output.GeneratePictureOutput;
import com.promptpicture.backend.entrypoint.rest.model.output.PromptOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class PromptAdapter {

    private final PromptFacade promptFacade;
    private final Prompt2PromptListOutputMapper prompt2PromptListOutputMapper;

    public GeneratePictureOutput generatePicture(String textPrompt, UUID userId) {

        var prmopt = promptFacade.create(textPrompt, userId);

        return GeneratePictureOutput.builder()
                .id(prmopt.getId())
                .promptText(prmopt.getPromptText())
                .b64Json(prmopt.getB64Json())
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
        promptFacade.createPrompt(createPromptInput.getPromptId());
    }

    public List<PromptOutput> getListOfPromptByUserId(UUID userId){
        var listOfPrompt = promptFacade.getListOfPromptByUserIdUseCase(userId);
        return prompt2PromptListOutputMapper.toListOfPromptOutput(listOfPrompt);
    }
}
