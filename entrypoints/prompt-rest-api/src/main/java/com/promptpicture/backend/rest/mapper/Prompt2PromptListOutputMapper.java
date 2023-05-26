package com.promptpicture.backend.rest.mapper;

import com.promptpicture.backend.core.prompt.domain.Prompt;
import com.promptpicture.backend.rest.model.output.PromptOutput;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface Prompt2PromptListOutputMapper {

    PromptOutput toPromptOutput(Prompt from);

    List<PromptOutput> toListOfPromptOutput(List<Prompt> from);
}
