package com.promptpicture.backend.core.prompt.use_case;

import com.promptpicture.backend.core.prompt.adapter.PromptRepositoryAdapter;
import com.promptpicture.backend.core.prompt.domain.Prompt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetListOfPromptByUserIdUseCase {

    private final PromptRepositoryAdapter promptRepositoryAdapter;

    public List<Prompt> execute(UUID userId) {
        return promptRepositoryAdapter.getListOfPromptByUserId(userId);
    }
}
