package com.promptpicture.backend.core.prompt.adapter;

import com.promptpicture.backend.core.prompt.domain.Prompt;

import java.util.List;
import java.util.UUID;

public interface PromptRepositoryAdapter {

    void savePromptPicture(Long promptId);

    Prompt savePromptPictureTemporarily(String promptText, String b64Json, UUID userId);

    List<Prompt> getListOfPrompt();

    Prompt findPromptById(Long id);

    List<Prompt> getListOfPromptByUserId(UUID userId);

    void deleteByPromptId(Long promptId);

}
