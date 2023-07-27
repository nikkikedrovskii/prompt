package com.promptpicture.backend.db_adapter.prompt.mapper;

import com.promptpicture.backend.core.prompt.domain.IndividualPrompt;
import com.promptpicture.backend.jpa.prompt.entity.PromptEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PromptEntity2IndividualPromptMapper {

    @Mapping(target = "b64Json", source = "promptPictureEntity.fileB64JsonText")
    @Mapping(target = "promptText", source = "promptPictureEntity.text")
    IndividualPrompt map(PromptEntity from);
}
