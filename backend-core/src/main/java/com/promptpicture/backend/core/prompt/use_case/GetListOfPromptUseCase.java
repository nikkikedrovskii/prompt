package com.promptpicture.backend.core.prompt.use_case;

import com.promptpicture.backend.core.prompt.adapter.PromptRepositoryAdapter;
import com.promptpicture.backend.core.prompt.domain.Prompt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetListOfPromptUseCase {

    private final PromptRepositoryAdapter promptRepositoryAdapter;

    public List<Prompt> execute() {
        return promptRepositoryAdapter.getListOfPrompt();
    }

}
