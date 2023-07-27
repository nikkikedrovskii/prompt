package com.promptpicture.backend.entrypoint.rest.mapper;

import com.promptpicture.backend.core.prompt.domain.CreateIndividualPrompt;
import com.promptpicture.backend.entrypoint.rest.model.input.CreateIndividualPromptRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CreateIndividualPromptRequest2CreateIndividualPromptMapper {

    @Mapping(target = "externalCustomerId", source = "userId")
    CreateIndividualPrompt map(CreateIndividualPromptRequest createIndividualPromptRequest);
}
