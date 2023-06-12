package com.promptpicture.backend.core.prompt.use_case;

import com.promptpicture.backend.core.prompt.adapter.PromptRepositoryAdapter;
import com.promptpicture.backend.core.tag.adapter.TagRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreatePromptUseCase {

    private final PromptRepositoryAdapter promptRepositoryAdapter;
    private final TagRepositoryAdapter tagRepositoryAdapter;

    public void execute(Long promptId, List<String> listOfTags) {
        if (!listOfTags.isEmpty()) {
            tagRepositoryAdapter.saveUserTag(listOfTags);
        }
        promptRepositoryAdapter.savePromptPicture(promptId, listOfTags);
    }
}
