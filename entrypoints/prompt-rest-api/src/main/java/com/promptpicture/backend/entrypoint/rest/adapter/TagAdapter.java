package com.promptpicture.backend.entrypoint.rest.adapter;

import com.promptpicture.backend.core.tag.TagFacade;
import com.promptpicture.backend.entrypoint.rest.mapper.Tag2TagOutputMapper;
import com.promptpicture.backend.entrypoint.rest.model.output.TagOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class TagAdapter {

    private final TagFacade tagFacade;
    private final Tag2TagOutputMapper tag2TagOutputMapper;

    public List<TagOutput> getListOfTag() {

        var listOfPrompt = tagFacade.getListOfTagUseCase();
        return tag2TagOutputMapper.toListOfPromptOutput(listOfPrompt);
    }
}
