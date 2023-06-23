package com.promptpicture.backend.entrypoint.rest.mapper;

import com.promptpicture.backend.core.prompt.domain.Prompt;
import com.promptpicture.backend.entrypoint.rest.model.output.PromptResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface Prompt2PromptListOutputMapper {

    PromptResponse toPromptOutput(Prompt from);

    List<PromptResponse> toListOfPromptOutput(List<Prompt> from);
}
