package com.promptpicture.backend.core.delle.component;

import com.promptpicture.backend.core.delle.adapter.DellEAdapter;
import com.promptpicture.backend.core.prompt.domain.CreateIndividualPrompt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DellEGeneratePictureComponent {

    private final DellEAdapter dellEAdapter;

    public String generatePictureFromPrompt(String promptText) {
       return dellEAdapter.generatePicture(promptText);
    }

    public String generateIndividualPrompt(CreateIndividualPrompt createIndividualPrompt) {
        return dellEAdapter.generateIndividualPrompt(createIndividualPrompt);
    }
}
