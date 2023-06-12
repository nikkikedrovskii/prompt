package com.promptpicture.backend.core.tag;

import com.promptpicture.backend.core.tag.domain.Tag;
import com.promptpicture.backend.core.tag.use_case.GetListOfTagUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TagFacade {

    private final GetListOfTagUseCase getListOfTagUseCase;

    public List<Tag> getListOfTagUseCase() {
        return getListOfTagUseCase.execute();
    }
}
