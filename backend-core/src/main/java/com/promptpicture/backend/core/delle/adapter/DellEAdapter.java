package com.promptpicture.backend.core.delle.adapter;

import com.promptpicture.backend.core.prompt.domain.CreateIndividualPrompt;

public interface DellEAdapter {

    String generatePicture(String promptText);

    String generateIndividualPrompt(CreateIndividualPrompt createIndividualPrompt);
}
