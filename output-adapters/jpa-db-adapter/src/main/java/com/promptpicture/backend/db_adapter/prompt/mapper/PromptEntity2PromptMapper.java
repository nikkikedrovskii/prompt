package com.promptpicture.backend.db_adapter.prompt.mapper;

import com.promptpicture.backend.core.prompt.domain.Prompt;
import com.promptpicture.backend.jpa.prompt.entity.PromptEntity;
import com.promptpicture.backend.jpa.tag.entity.TagEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PromptEntity2PromptMapper {

    @Mapping(target = "b64Json", source = "promptPictureEntity.fileB64JsonText")
    @Mapping(target = "promptText", source = "promptPictureEntity.text")
    @Mapping(target = "tags", expression = "java(toListOfTags(from.getPromptTags()))")
    Prompt toPrompt(PromptEntity from);

    List<Prompt> toListOfPrompt(List<PromptEntity> from);

    default List<String> toListOfTags(List<TagEntity> promptTags){
        if (promptTags != null) {
            return promptTags.stream().map(TagEntity::getTagName).toList();
        }
        return Collections.emptyList();
    }
}
