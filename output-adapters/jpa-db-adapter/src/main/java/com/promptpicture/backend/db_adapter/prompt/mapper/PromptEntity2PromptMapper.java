package com.promptpicture.backend.db_adapter.prompt.mapper;

import com.promptpicture.backend.core.prompt.domain.Prompt;
import com.promptpicture.backend.jpa.prompt.entity.PromptEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PromptEntity2PromptMapper {

    @Mapping(target = "b64Json", source = "promptPictureEntity.fileB64JsonText")
    @Mapping(target = "promptText", source = "promptPictureEntity.text")
    Prompt toPrompt(PromptEntity from);

    List<Prompt> toListOfPrompt(List<PromptEntity> from);
}
