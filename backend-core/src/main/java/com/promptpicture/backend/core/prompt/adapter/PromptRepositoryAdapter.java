package com.promptpicture.backend.core.prompt.adapter;

import com.promptpicture.backend.core.prompt.domain.Prompt;
import com.promptpicture.backend.core.prompt.domain.PromptFilter;

import java.util.List;
import java.util.UUID;

public interface PromptRepositoryAdapter {

    void savePromptPicture(Long promptId, List<String> listOfTags);

    Prompt savePromptPictureTemporarily(String promptText, String b64Json, UUID userId);

    List<Prompt> getListOfPrompt(PromptFilter promptFilter);

    Prompt findPromptById(Long id);

    List<Prompt> getListOfPromptByUserId(UUID userId);

    void deleteByPromptId(Long promptId);

    List<Prompt> getListOfAllPrompts();

}
