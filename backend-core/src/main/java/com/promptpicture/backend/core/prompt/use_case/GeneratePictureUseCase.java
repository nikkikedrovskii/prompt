package com.promptpicture.backend.core.prompt.use_case;

import com.promptpicture.backend.core.delle.component.DellEGeneratePictureComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeneratePictureUseCase {

    private final DellEGeneratePictureComponent dellEGeneratePictureComponent;

    public String execute(String promptText) {
        return dellEGeneratePictureComponent.generatePictureFromPrompt(promptText);
    }
}
