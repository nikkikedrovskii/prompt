package com.promptpicture.backend.core.prompt.use_case;

import com.promptpicture.backend.core.prompt.adapter.PromptRepositoryAdapter;
import com.promptpicture.backend.core.prompt.domain.Prompt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClearPromptHistoryUseCase {

    private static final Integer PROMPT_LIFE_TIME_HOURS = 12;
    private final PromptRepositoryAdapter promptRepositoryAdapter;

    public void execute() {

        var listOfAllPrompts = promptRepositoryAdapter.getListOfAllPrompts();
        var listOfPromptForDelete = getListOfPromptForDelete(listOfAllPrompts);
        listOfPromptForDelete.forEach(prompt -> promptRepositoryAdapter.deleteByPromptId(prompt.getId()));
    }

  private List<Prompt> getListOfPromptForDelete(List<Prompt> listOfAllPrompts) {
        return listOfAllPrompts.stream()
                .filter(prompt -> !prompt.isSaved())
                .filter( prompt -> ChronoUnit.HOURS.between(prompt.getCreatedAt(), OffsetDateTime.now()) > PROMPT_LIFE_TIME_HOURS )
                .toList();
    }


}
