package com.promptpicture.backend.core.prompt;

import com.promptpicture.backend.core.prompt.domain.Prompt;
import com.promptpicture.backend.core.prompt.use_case.CreatePromptUseCase;
import com.promptpicture.backend.core.prompt.use_case.GeneratePictureUseCase;
import com.promptpicture.backend.core.prompt.use_case.GetListOfPromptUseCase;
import com.promptpicture.backend.core.prompt.use_case.GetPromptDetailUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PromptFacade {

    private final GeneratePictureUseCase generatePictureUseCase;
    private final GetListOfPromptUseCase getListOfPromptUseCase;
    private final GetPromptDetailUseCase getPromptDetailUseCase;
    private final CreatePromptUseCase createPromptUseCase;

    public String create(String promptText) {
       return generatePictureUseCase.execute(promptText);
    }

    public List<Prompt> getListOfPromptUseCase() {
        return getListOfPromptUseCase.execute();
    }

    public Prompt getPromptDetailUseCase(Long id){
        return getPromptDetailUseCase.execute(id);
    }

    public void createPrompt(String promptText, String b64Json){
        createPromptUseCase.execute(promptText, b64Json);
    }
}
