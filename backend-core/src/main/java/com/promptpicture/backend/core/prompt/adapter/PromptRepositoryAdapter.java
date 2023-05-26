package com.promptpicture.backend.core.prompt.adapter;

import com.promptpicture.backend.core.prompt.domain.Prompt;

import java.util.List;

public interface PromptRepositoryAdapter {

    void savePromptPicture(String promptText, String b64Json);

    List<Prompt> getListOfPrompt();

    Prompt findPromptById(Long id);

}
