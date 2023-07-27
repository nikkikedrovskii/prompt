package com.promptpicture.backend.core.prompt.use_case;

import com.promptpicture.backend.core.delle.component.DellEGeneratePictureComponent;
import com.promptpicture.backend.core.prompt.adapter.PromptRepositoryAdapter;
import com.promptpicture.backend.core.prompt.domain.CreateIndividualPrompt;
import com.promptpicture.backend.core.prompt.domain.IndividualPrompt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveIndividualPromptUseCase {

    private final DellEGeneratePictureComponent dellEGeneratePictureComponent;
    private final PromptRepositoryAdapter promptRepositoryAdapter;

    public IndividualPrompt execute(CreateIndividualPrompt createIndividualPrompt) {
        var generatedPrompt = dellEGeneratePictureComponent.generateIndividualPrompt(createIndividualPrompt);
        return promptRepositoryAdapter.saveIndividualPrompt(
                createIndividualPrompt.getPromptText(),
                generatedPrompt,
                createIndividualPrompt.getExternalCustomerId()
        );
    }


}
