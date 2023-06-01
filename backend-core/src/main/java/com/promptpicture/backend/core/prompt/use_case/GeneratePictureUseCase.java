package com.promptpicture.backend.core.prompt.use_case;

import com.promptpicture.backend.core.delle.component.DellEGeneratePictureComponent;
import com.promptpicture.backend.core.prompt.adapter.PromptRepositoryAdapter;
import com.promptpicture.backend.core.prompt.domain.Prompt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GeneratePictureUseCase {

    private final DellEGeneratePictureComponent dellEGeneratePictureComponent;
    private final PromptRepositoryAdapter promptRepositoryAdapter;

    public Prompt execute(String promptText, UUID userId) {
        var b64Json = dellEGeneratePictureComponent.generatePictureFromPrompt(promptText);
        var listOfPromptByUserId = promptRepositoryAdapter.getListOfPromptByUserId(userId);
        if (listOfPromptByUserId.size() == 5){
            var prompt = listOfPromptByUserId.stream().findFirst().get();
            promptRepositoryAdapter.deleteByPromptId(prompt.getId());
        }

        return  savePictureTemporarily(b64Json,promptText,userId);
    }

    private Prompt savePictureTemporarily(String b64Json, String promptText, UUID userId){
          return promptRepositoryAdapter.savePromptPictureTemporarily(promptText,b64Json, userId);
    }
}
