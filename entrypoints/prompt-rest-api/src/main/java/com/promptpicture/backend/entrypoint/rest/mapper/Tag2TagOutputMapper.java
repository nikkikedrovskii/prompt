package com.promptpicture.backend.entrypoint.rest.mapper;

import com.promptpicture.backend.core.tag.domain.Tag;
import com.promptpicture.backend.entrypoint.rest.model.output.TagResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface Tag2TagOutputMapper {

    TagResponse toPromptOutput(Tag from);

    List<TagResponse> toListOfPromptOutput(List<Tag> from);
}
