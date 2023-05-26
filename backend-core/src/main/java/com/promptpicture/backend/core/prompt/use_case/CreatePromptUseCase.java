package com.promptpicture.backend.core.prompt.use_case;

import com.promptpicture.backend.core.prompt.adapter.PromptRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePromptUseCase {

    private final PromptRepositoryAdapter promptRepositoryAdapter;

    public void execute(String promptText, String b64Json) {
        promptRepositoryAdapter.savePromptPicture(promptText,b64Json);
    }
}
