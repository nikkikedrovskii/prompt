package com.promptpicture.backend.entrypoint.rest.adapter;

import com.promptpicture.backend.core.prompt.PromptFacade;
import com.promptpicture.backend.core.prompt.domain.PromptFilter;
import com.promptpicture.backend.entrypoint.rest.mapper.CreateIndividualPromptRequest2CreateIndividualPromptMapper;
import com.promptpicture.backend.entrypoint.rest.mapper.Prompt2PromptListOutputMapper;
import com.promptpicture.backend.entrypoint.rest.model.input.CreateIndividualPromptRequest;
import com.promptpicture.backend.entrypoint.rest.model.input.CreatePromptRequest;
import com.promptpicture.backend.entrypoint.rest.model.output.CreateIndividualPromptResponse;
import com.promptpicture.backend.entrypoint.rest.model.output.GeneratePictureResponse;
import com.promptpicture.backend.entrypoint.rest.model.output.PromptResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class PromptAdapter {

    private final PromptFacade promptFacade;
    private final Prompt2PromptListOutputMapper prompt2PromptListOutputMapper;
    private final CreateIndividualPromptRequest2CreateIndividualPromptMapper createIndividualPromptRequest2CreateIndividualPromptMapper;

    public GeneratePictureResponse generatePicture(String textPrompt, UUID userId) {

        var prmopt = promptFacade.create(textPrompt, userId);

        return GeneratePictureResponse.builder()
                .id(prmopt.getId())
                .promptText(prmopt.getPromptText())
                .b64Json(prmopt.getB64Json())
                .build();
    }

    public List<PromptResponse> getListOfPrompt(PromptFilter promptFilter) {

        var listOfPrompt = promptFacade.getListOfPromptUseCase(promptFilter);

        return prompt2PromptListOutputMapper.toListOfPromptOutput(listOfPrompt);
    }

    public PromptResponse getPromptDetail(Long id) {
        var prompt = promptFacade.getPromptDetailUseCase(id);
        return prompt2PromptListOutputMapper.toPromptOutput(prompt);
    }

    public void createPrompt(CreatePromptRequest createPromptRequest) {
        promptFacade.createPrompt(createPromptRequest.getPromptId(), createPromptRequest.getListOfTags());
    }

    public CreateIndividualPromptResponse saveIndividualPrompt(CreateIndividualPromptRequest createIndividualPromptRequest) {
        var createIndividualPrompt = createIndividualPromptRequest2CreateIndividualPromptMapper.map(createIndividualPromptRequest);
        var individualPrompt = promptFacade.saveIndividualPrompt(createIndividualPrompt);
        return CreateIndividualPromptResponse.builder()
                .id(individualPrompt.getId())
                .promptText(individualPrompt.getPromptText())
                .b64Json(individualPrompt.getB64Json())
                .build();
    }

    public List<PromptResponse> getListOfPromptByUserId(UUID userId){
        var listOfPrompt = promptFacade.getListOfPromptByUserIdUseCase(userId);
        return prompt2PromptListOutputMapper.toListOfPromptOutput(listOfPrompt);
    }
}
