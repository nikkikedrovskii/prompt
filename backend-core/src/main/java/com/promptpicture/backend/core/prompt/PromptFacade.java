package com.promptpicture.backend.core.prompt;

import com.promptpicture.backend.core.prompt.domain.CreateIndividualPrompt;
import com.promptpicture.backend.core.prompt.domain.IndividualPrompt;
import com.promptpicture.backend.core.prompt.domain.Prompt;
import com.promptpicture.backend.core.prompt.domain.PromptFilter;
import com.promptpicture.backend.core.prompt.use_case.CreatePromptUseCase;
import com.promptpicture.backend.core.prompt.use_case.GeneratePictureUseCase;
import com.promptpicture.backend.core.prompt.use_case.GetListOfPromptByUserIdUseCase;
import com.promptpicture.backend.core.prompt.use_case.GetListOfPromptUseCase;
import com.promptpicture.backend.core.prompt.use_case.GetPromptDetailUseCase;
import com.promptpicture.backend.core.prompt.use_case.SaveIndividualPromptUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PromptFacade {

    private final GeneratePictureUseCase generatePictureUseCase;
    private final GetListOfPromptUseCase getListOfPromptUseCase;
    private final GetPromptDetailUseCase getPromptDetailUseCase;
    private final CreatePromptUseCase createPromptUseCase;
    private final GetListOfPromptByUserIdUseCase getListOfPromptByUserIdUseCase;
    private final SaveIndividualPromptUseCase saveIndividualPromptUseCase;

    public Prompt create(String promptText, UUID userId) {
       return generatePictureUseCase.execute(promptText, userId);
    }

    public IndividualPrompt saveIndividualPrompt(CreateIndividualPrompt createIndividualPrompt) {
        return saveIndividualPromptUseCase.execute(createIndividualPrompt);
    }

    public List<Prompt> getListOfPromptUseCase(PromptFilter promptFilter) {
        return getListOfPromptUseCase.execute(promptFilter);
    }

    public Prompt getPromptDetailUseCase(Long id){
        return getPromptDetailUseCase.execute(id);
    }

    public void createPrompt(Long promptId, List<String> listOfTags){
        createPromptUseCase.execute(promptId, listOfTags);
    }

    public List<Prompt> getListOfPromptByUserIdUseCase(UUID userId) {
        return getListOfPromptByUserIdUseCase.execute(userId);
    }

}
