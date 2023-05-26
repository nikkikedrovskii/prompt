package com.promptpicture.backend.core.prompt.use_case;

import com.promptpicture.backend.core.prompt.adapter.PromptRepositoryAdapter;
import com.promptpicture.backend.core.prompt.domain.Prompt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetPromptDetailUseCase {

    private final PromptRepositoryAdapter promptRepositoryAdapter;

    public Prompt execute(Long id) {
        return promptRepositoryAdapter.findPromptById(id);
    }
}
